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

import com.springboot.jpa.models.Color;
import com.springboot.jpa.service.ColorService;

@CrossOrigin
@RestController
@RequestMapping("/colors")
@PreAuthorize("hasAnyRole('AddNew', 'ADMIN')")
public class RestColorController {
	// beans
	@Autowired
	private ColorService colorService;
	
	
	// get all color
//	@PreAuthorize("hasAnyRole('Accountant','ADMIN','AddNewProduct')")
	@GetMapping
	public List<Color> listAll() {
		return colorService.listAll();
	}

	// get id color
	@GetMapping("/{id}")
	public Color getId(@PathVariable("id") Integer id) {
		return colorService.findById(id);
	}

	// update size of product
	@PutMapping(value="" , consumes = "multipart/form-data")
	public Color updateColorId(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		Color color =colorService.findById(id);
		color.setName(name);
		return colorService.save(color);
	}

	// delete by id size of product
	@DeleteMapping("/{id}")
	public void deleteColorId(@PathVariable("id") Integer id) {
		Color color = colorService.findById(id);
		colorService.deleteById(color.getId());
	}

	// add size of product
	@PostMapping(value="", consumes = "multipart/form-data")
	public Color createColor(@RequestParam("name") String name) {
		Color savedColor = new Color();
		savedColor.setName(name);
		return colorService.save(savedColor);
	}

}
