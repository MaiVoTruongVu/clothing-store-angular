package com.springboot.jpa.rest.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.jpa.models.Image;
import com.springboot.jpa.models.Product;
import com.springboot.jpa.service.ImageService;
import com.springboot.jpa.service.ProductService;
import com.springboot.jpa.utils.fileUploadUtils;

@CrossOrigin
@RestController
@RequestMapping("/img")
@PreAuthorize("hasAnyRole('AddNew', 'ADMIN')")
public class RestImageController {
	// beans
	@Autowired
	private ImageService imageService;

	// beans
	@Autowired
	private ProductService proService;

	// beans
	@Value("${file.upload-dir}")
	private String UPLOADED_FOLDER;

	// get all image
	@GetMapping("")
	public List<Image> listAll() {
		return this.imageService.listAll();
	}

	// search image of product
	@GetMapping("/getProductById/{id}")
	public List<Image> getProductById(@PathVariable("id") Integer productId) {
		return this.imageService.getProductById(productId);
	}

	// get image by id
	@GetMapping("/{id}")
	public Image getId(@PathVariable("id") Integer id) {
		return this.imageService.findById(id);
	}

	// delete image by id
	@DeleteMapping("/{id}")
	public void deleteId(@PathVariable("id") Integer id) {
		Image image = imageService.findById(id);
		imageService.deleteById(image.getId());
	}

	// add image
	@PostMapping(value = "/uploadfileImage", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadFiles(HttpServletRequest req, @RequestParam("product") Integer productId,
			@RequestParam("files") MultipartFile[] files) {

		String filess = "";
		List<Image> listImage = new ArrayList<Image>();
		Product pro2 = new Product();
		pro2 = proService.findById(productId);

		try {
			List<String> fileNames = new ArrayList<>();
			for (MultipartFile uploadedFile : files) {
				fileNames.add(fileUploadUtils.saveUploadedFile(uploadedFile, req.getServletContext().getRealPath("/imageProduct/")));
				File file = new File(UPLOADED_FOLDER + pro2.getId() + "-" + uploadedFile.getOriginalFilename());
				uploadedFile.transferTo(file);
			}
			System.out.println(req.getServletContext().getRealPath("/imageProduct/"));
			for (int i = 0; i < fileNames.size(); i++) {
				filess = fileNames.get(i);
				Image img = new Image();
				img.setProduct(pro2);
				img.setUrl("http://localhost:8080/img/getImage/" + filess);
				img.setName(pro2.getId() + "-" + filess);
				imageService.save(img);
				listImage.add(img);
			}
			return ResponseEntity.status(HttpStatus.OK).body(listImage);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Fail to upload files!");
		}

	}

//	 update image
//		@PutMapping(consumes = "multipart/form-data")
//		public ResponseEntity<?> updateUploadFiles(HttpServletRequest request, @RequestParam("productId") Integer productId,
//				@RequestParam("files") MultipartFile[] files , @RequestParam("id") Integer imageId) {
//
//			String oneFile = "";
//			List<Image> listImage = new ArrayList<Image>();
//			Product pro = new Product();
//			pro = proService.findById(productId);
//
//			try {
//				List<String> fileNames = new ArrayList<>();
//				for (MultipartFile uploadedFile : files) {
//					fileUploadUtils.saveUploadedFile(uploadedFile,
//							request.getServletContext().getRealPath("/imageProduct/"));
//					File file = new File(UPLOADED_FOLDER + pro.getId() + "-" + uploadedFile.getOriginalFilename());
//					uploadedFile.transferTo(file);
//					fileNames.add(uploadedFile.getOriginalFilename());
//				}
//				System.out.println(request.getServletContext().getRealPath("/imageProduct/"));
//				for (int i = 0; i < fileNames.size(); i++) {
//					oneFile = fileNames.get(i);
//					Image img = new Image();
//					img.setProduct(pro);
//					img.setUrl("http://localhost:8080/imageProduct/getImage/"+ oneFile);
//					img.setName(pro.getId() + "-" + oneFile);
//					imageService.save(img);
//					listImage.add(img);
//				}
//				return ResponseEntity.status(HttpStatus.OK).body(listImage);
//			} catch (Exception e) {
//				e.printStackTrace();
//				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Fail to upload files!");
//			}
//		}

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

}
