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

import com.springboot.jpa.models.Producer;
import com.springboot.jpa.service.ProducerService;

@CrossOrigin
@RestController
@RequestMapping("/producers")
@PreAuthorize("hasAnyRole('AddNew', 'ADMIN')")
public class RestProducerController {
	// beans
	@Autowired
	private ProducerService producerService;

	// get all producer
//	@PreAuthorize("hasAnyRole('Accountant','ADMIN','AddNewProduct')")
	@GetMapping("")
	public List<Producer> listAll() {
		return producerService.listAll();
	}

	// get id producer
	@GetMapping("/{id}")
	public Producer getId(@PathVariable("id") Integer id) {
		return producerService.findById(id);
	}

	// update producer
//	@PutMapping("/{id}")
//	public Producer updateProducer(@PathVariable("id") Integer id, @RequestBody @Valid Producer Producer) {
//		producerService.findById(id);
//		return producerService.save(Producer);
//	}

	// delete by id of producer
	@DeleteMapping("/{id}")
	public void deleteProducerId(@PathVariable("id") Integer id) {
		Producer producer = producerService.findById(id);
		producerService.deleteById(producer.getId());
	}

	// add producer
//	@PostMapping("")
//	public ResponseEntity<?> createProducer(@RequestBody @Valid Producer Producer) {
//		Producer savedProducer = producerService.save(Producer);
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.set("MyResponseHeader", "MyValue");
//		return new ResponseEntity<>(savedProducer, responseHeaders, HttpStatus.CREATED);
//	}
	
	@PostMapping(value="" , consumes = "multipart/form-data")
	public Producer createProducer(@RequestParam("name") String name , 
			@RequestParam("address") String address , @RequestParam("phone") String phone) {
		Producer savedProducer = new Producer();
		savedProducer.setAddress(address);
		savedProducer.setName(name);
		savedProducer.setPhone(phone);
		return this.producerService.save(savedProducer);
	}
	@PutMapping(value="" , consumes = "multipart/form-data")
	public Producer updateProducer(@RequestParam("id") Integer id,@RequestParam("name") String name , 
			@RequestParam("address") String address , @RequestParam("phone") String phone) {
		Producer savedProducer = this.producerService.findById(id);
		savedProducer.setAddress(address);
		savedProducer.setName(name);
		savedProducer.setPhone(phone);
		return this.producerService.save(savedProducer);
	}

}
