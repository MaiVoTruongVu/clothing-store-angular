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

import com.springboot.jpa.models.Banner;
import com.springboot.jpa.service.BannerService;
import com.springboot.jpa.utils.fileUploadUtils;

@CrossOrigin
@RestController
@RequestMapping("/banners")
@PreAuthorize("hasAnyRole('AddNew', 'ADMIN')")
public class RestBannerController {
	// beans
	@Autowired
	private BannerService bannerService;
	// beans
	@Value("${file.upload-dir}")
	private String UPLOADED_FOLDER;

	// get all banner
	@GetMapping("")
	public List<Banner> listAll() {
		return bannerService.listAll();
	}

	// get id banner
	@GetMapping("/{id}")
	public Banner getId(@PathVariable("id") Integer id) {
		return bannerService.findById(id);
	}

	// delete banner by id
	@DeleteMapping("/{id}")
	public void deleteBannerId(@PathVariable("id") Integer id) {
		Banner banner = bannerService.findById(id);
		bannerService.deleteById(banner.getId());
	}

	// update banner
	@PutMapping(consumes = "multipart/form-data")
	public Banner update(HttpServletRequest request, @RequestParam("id") Integer id,
			@RequestParam("url") MultipartFile photo, @RequestParam("content") String content,
			@RequestParam("title") String title) {
		Banner banner = bannerService.findById(id);
		try {
			fileUploadUtils.deleteUploadFile(banner.getUrl(),
					request.getServletContext().getRealPath("/imageProduct/"));
			byte[] bytes = photo.getBytes();
			banner.setContent(content);
			banner.setTitle(title);
			banner.setUrl(
					fileUploadUtils.saveUploadedFile(photo, request.getServletContext().getRealPath("/imageProduct/")));
			banner = bannerService.save(banner);
			System.out.println(request.getServletContext().getRealPath("/imageProduct/"));
			Path path = Paths.get(UPLOADED_FOLDER + banner.getId() + "-" + photo.getOriginalFilename());
			Files.write(path, bytes);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		banner.setUrl("http://localhost:8080/banners/getImage/" + banner.getUrl());
		bannerService.save(banner);
		return banner;
	}
	// update banner
		@PutMapping(value="/updateNotFile", consumes = "multipart/form-data")
		public Banner updateNotFile(HttpServletRequest request, @RequestParam("id") Integer id, @RequestParam("content") String content,
				@RequestParam("title") String title) {
			Banner banner = bannerService.findById(id);
				banner.setContent(content);
				banner.setTitle(title);
				banner = bannerService.save(banner);
			bannerService.save(banner);
			return banner;
		}

	// add banner
	@PostMapping(consumes = "multipart/form-data")
	public Banner save(HttpServletRequest request, @RequestParam("url") MultipartFile photo,
			@RequestParam("content") String content, @RequestParam("title") String title) {
		Banner banner1 = new Banner();
		try {
			byte[] bytes = photo.getBytes();

			banner1.setContent(content);
			banner1.setTitle(title);
			banner1 = bannerService.save(banner1);

			Path path = Paths.get(UPLOADED_FOLDER + banner1.getId() + "-" + photo.getOriginalFilename());
			Files.write(path, bytes);

			System.out.println(request.getServletContext().getRealPath("/imageProduct/"));

			banner1.setUrl(fileUploadUtils.saveUploadedFile(photo,
					request.getServletContext().getRealPath("/imageProduct/")));

		} catch (IOException e) {
			e.printStackTrace();
		}
		banner1.setUrl("http://localhost:8080/banners/getImage/" + banner1.getUrl());
		bannerService.save(banner1);
		return banner1;
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
}
