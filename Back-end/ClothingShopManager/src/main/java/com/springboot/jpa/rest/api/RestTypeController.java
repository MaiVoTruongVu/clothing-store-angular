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

import com.springboot.jpa.models.Type;
import com.springboot.jpa.service.TypeService;

@CrossOrigin
@RestController
@RequestMapping("/types")
@PreAuthorize("hasAnyRole('AddNew', 'ADMIN')")
public class RestTypeController {
	// beans
	@Autowired
	private TypeService typeService;

	// get all type of product
	
//	@PreAuthorize("hasAnyRole('Accountant','ADMIN','AddNewProduct')")
	@GetMapping
	public List<Type> listAll() {
		return typeService.listAll();
	}

	// get id type of product
	@GetMapping("/{id}")
	public Type getId(@PathVariable("id") Integer id) {
		return typeService.findById(id);
	}

	// update type of product
	@PutMapping(value ="" , consumes = "multipart/form-data")
	public Type updateTypeId(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		Type type = typeService.findById(id);
		type.setName(name);
		return typeService.save(type);
	}

	// delete by id type of product
	@DeleteMapping("/{id}")
	public void deleteTypeId(@PathVariable("id") Integer id) {
		Type Type = typeService.findById(id);
		typeService.deleteById(Type.getId());
	}

	// add type of product
	@PostMapping(value ="" , consumes = "multipart/form-data")
	public Type createType(@RequestParam("name") String name) {
		Type savedType = new Type();
		savedType.setName(name);
		return typeService.save(savedType);
	}

}
