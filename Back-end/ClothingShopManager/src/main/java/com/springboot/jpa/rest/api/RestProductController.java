package com.springboot.jpa.rest.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.jpa.models.Gender;
import com.springboot.jpa.models.Product;
import com.springboot.jpa.repositories.ColorRepository;
import com.springboot.jpa.repositories.SizeRepository;
import com.springboot.jpa.service.ProducerService;
import com.springboot.jpa.service.ProductService;
import com.springboot.jpa.service.TypeService;
import com.springboot.jpa.utils.fileUploadUtils;

@CrossOrigin
@RestController
@RequestMapping("/products")
@PreAuthorize("hasAnyRole('AddNew', 'ADMIN')")
public class RestProductController {
	// beans
	@Autowired
	private ProductService productService;

	// beans
	@Autowired
	private ProducerService producerService;

	// beans
	@Autowired
	private TypeService typeService;

	// beans
	@Autowired
	private ColorRepository colorRepo;

	// beans
	@Autowired
	private SizeRepository sizeRepo;

	// beans
	@Value("${file.upload-dir}")
	private String UPLOADED_FOLDER;

	// get all product
//	@PreAuthorize("hasAnyRole('Accountant', 'ADMIN','AddNewProduct')")
	@GetMapping("")
	public List<Product> listAll() {
		return this.productService.listAll();
	}

	@GetMapping("/gender/{gender}")
	public List<Product> getGender(@PathVariable("gender") Gender gender) {
		return this.productService.getGender(gender);
	}

//	@GetMapping("/gender/{gender}/type/{typeId}")
//	public List<Product> getGenderAndType(@PathVariable("gender") Gender gender,
//			@PathVariable("typeId") Integer typeId) {
//		return this.productService.getGenderAndType(gender, typeId);
//	}

	// get id product
	@GetMapping("/{id}")
	public Product getId(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}

	@GetMapping("/size/{id}")
	public List<Product> getSize(@PathVariable("id") Integer id) {
		return productService.getSize(id);
	}

	// delete product by id
	@DeleteMapping("/{id}")
	public void deleteProductId(@PathVariable("id") Integer id) {
		Product product = productService.findById(id);
		productService.deleteById(product.getId());
	}

	// add product
	@PostMapping(value = "", consumes = "multipart/form-data")
	public Product save(HttpServletRequest request, @RequestParam("avatar") MultipartFile photo,
			@RequestParam("code") String code, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("price") Double price,
			@RequestParam("isHot") Boolean isHot, @RequestParam("gender") Gender gender,
			@RequestParam("type") Integer typeId, @RequestParam("size") Integer sizeId,
			@RequestParam("color") Integer colorId, @RequestParam("producer") Integer producerId) {
		Product product = new Product();
		try {
			byte[] bytes = photo.getBytes();

			product.setCode(code);
			product.setName(name);
			product.setDescription(description);
			product.setPrice(price);
			product.setIsHot(isHot);
			product.setGender(gender);
			product.setSize(sizeRepo.findById(sizeId).get());
			product.setColor(colorRepo.findById(colorId).get());
			product.setType(typeService.findById(typeId));
			product.setProducer(producerService.findById(producerId));
			product = productService.save(product);

			Path path = Paths.get(UPLOADED_FOLDER + product.getId() + "-" + photo.getOriginalFilename());
			Files.write(path, bytes);
			System.out.println(request.getServletContext().getRealPath("/imageProduct/"));
			product.setAvatar(
					fileUploadUtils.saveUploadedFile(photo, request.getServletContext().getRealPath("/imageProduct/")));

		} catch (IOException e) {
			e.printStackTrace();
		}
		product.setAvatar("http://localhost:8080/products/getImage/" + product.getAvatar());
		productService.save(product);
		return product;
	}

	// update product by id
	@PutMapping(value = "", consumes = "multipart/form-data")
	public Product update(HttpServletRequest request, @RequestParam("avatar") MultipartFile photo,
			@RequestParam("id") Integer productId, @RequestParam("code") String code, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("price") Double price,
			@RequestParam("isHot") Boolean isHot, @RequestParam("gender") Gender gender,
			@RequestParam("type") Integer typeId, @RequestParam("size") Integer sizeId,
			@RequestParam("color") Integer colorId, @RequestParam("producer") Integer producerId) {
		Product product = productService.findById(productId);
		try {
			fileUploadUtils.deleteUploadFile(product.getAvatar(),
					request.getServletContext().getRealPath("/imageProduct/"));
			byte[] bytes = photo.getBytes();

			product.setCode(code);
			product.setName(name);
			product.setDescription(description);
			product.setPrice(price);
			product.setIsHot(isHot);
			product.setGender(gender);
			product.setSize(sizeRepo.findById(sizeId).get());
			product.setColor(colorRepo.findById(colorId).get());
			product.setType(typeService.findById(typeId));
			product.setProducer(producerService.findById(producerId));
			product = productService.save(product);
			System.out.println(request.getServletContext().getRealPath("/imageProduct/"));
			Path path = Paths.get(UPLOADED_FOLDER + product.getId() + "-" + photo.getOriginalFilename());
			Files.write(path, bytes);

			product.setAvatar(
					fileUploadUtils.saveUploadedFile(photo, request.getServletContext().getRealPath("/imageProduct/")));

		} catch (IOException e) {
			e.printStackTrace();
		}
		product.setAvatar("http://localhost:8080/products/getImage/" + product.getAvatar());
		productService.save(product);
		return product;
	}

	// update product by id
	@PutMapping(value = "/updateNotFile", consumes = "multipart/form-data")
	public Product updateNotFile(HttpServletRequest request, @RequestParam("id") Integer productId,
			@RequestParam("code") String code, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("price") Double price,
			@RequestParam("isHot") Boolean isHot, @RequestParam("gender") Gender gender,
			@RequestParam("type") Integer typeId, @RequestParam("size") Integer sizeId,
			@RequestParam("color") Integer colorId, @RequestParam("producer") Integer producerId) {
		Product product = productService.findById(productId);
		product.setCode(code);
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setIsHot(isHot);
		product.setGender(gender);
		product.setSize(sizeRepo.findById(sizeId).get());
		product.setColor(colorRepo.findById(colorId).get());
		product.setType(typeService.findById(typeId));
		product.setProducer(producerService.findById(producerId));
		product = productService.save(product);
		productService.save(product);
		return product;
	}

	// get Image
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/getImage/{fileName:.+}", method = RequestMethod.GET)

	public String showImage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws Exception {

		System.out.println(fileName);
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

		try {
			System.out.println(request.getServletContext().getRealPath("/imageProduct/") + File.separator + fileName);

			BufferedImage image = ImageIO.read(
					new File(request.getServletContext().getRealPath("/imageProduct/") + File.separator + fileName));
			ImageIO.write(image, "jpeg", jpegOutputStream);
		} catch (Exception e) {
			return e.getMessage();
		}

		byte[] imgByte = jpegOutputStream.toByteArray();

		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(imgByte);
		responseOutputStream.flush();
		responseOutputStream.close();

		return "OK";
	}

	// search code of product
	@GetMapping("/searchCode/{code}")
	public List<Product> searchCode(@PathVariable("code") String code) {
		return this.productService.findByCode(code);
	}

	// search code of product
	@GetMapping("/searchName/{name}")
	public List<Product> searchName(@PathVariable("name") String name) {
		return this.productService.findByName(name);
	}

	// search min price or max price
	@GetMapping("/searchMinMax/{min}/{max}")
	public List<Product> searchMinMaxPrice(@PathVariable("min") Double min, @PathVariable("max") Double max) {
		return this.productService.findbyPriceMinMax(min, max);
	}

	// get product by size
	@GetMapping("/searchSize/{id}")
	public List<Product> findProductBySize(@PathVariable("id") Integer sizeId) {
		return this.productService.findProductBySize(sizeId);
	}

	// get product by type
	@GetMapping("/searchType/{id}")
	public List<Product> findProductByType(@PathVariable("id") Integer typeId) {
		return this.productService.findProductByType(typeId);
	}

	// get product by color
	@GetMapping("/searchColor/{id}")
	public List<Product> findProductByColor(@PathVariable("id") Integer colorId) {
		return this.productService.findProductByColor(colorId);
	}

	// get product by gender
	@GetMapping("/searchGender/{num}")
	public List<Product> findProductByGender(@PathVariable("num") Integer number) {
		return this.productService.findProductByGender(number);
	}

}
