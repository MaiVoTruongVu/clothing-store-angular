package com.springboot.jpa.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.models.Size;
import com.springboot.jpa.service.SizeService;

@CrossOrigin
@RestController
@RequestMapping("/sizes")
@PreAuthorize("hasAnyRole('AddNew', 'ADMIN')")
public class RestSizeController {
	// beans
	@Autowired
	private SizeService sizeService;

	// get all size
//	@PreAuthorize("hasAnyRole('Accountant','ADMIN','AddNewProduct')")
	@GetMapping
	public List<Size> listAll() {
		return sizeService.listAll();
	}

	// get id size
	@GetMapping("/{id}")
	public Size getId(@PathVariable("id") Integer id) {
		return sizeService.findById(id);
	}

	// update size of product
	@PutMapping(value="", consumes = "multipart/form-data")
	public Size updateSize(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		Size savedSize = sizeService.findById(id);
		savedSize.setName(name);
		return sizeService.save(savedSize);
	}

	// delete by id size of product
	@DeleteMapping("/{id}")
	public void deleteSizeId(@PathVariable("id") Integer id) {
		Size size = sizeService.findById(id);
		sizeService.deleteById(size.getId());
	}

	// add size of product
	@PostMapping(value="", consumes = "multipart/form-data")
	public Size createSize(@RequestParam("name") String name) {
		Size savedSize = new Size();
		savedSize.setName(name);
		return sizeService.save(savedSize);
	}

}
