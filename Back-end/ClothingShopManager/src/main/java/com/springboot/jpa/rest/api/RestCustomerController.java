package com.springboot.jpa.rest.api;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.models.Customer;
import com.springboot.jpa.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/customers")
@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
public class RestCustomerController {
	// beans
	@Autowired
	private CustomerService customerService;

	// get all customer
	@GetMapping
	public List<Customer> listAll() {
		return customerService.listAll();
	}

	// get customer by id
	@GetMapping("/{id}")
	public Customer getId(@PathVariable("id") Integer id) {
		return customerService.findById(id);
	}

	// search first name of customer
	@GetMapping("/searchName/{firstName}")
	public List<Customer> findByFirstName(@PathVariable("firstName") String firstName) {
		return customerService.findbyFirstName(firstName);
	}

	// search phone of customer
	@GetMapping("/searchPhone/{phone}")
	public Customer findByPhone(@PathVariable("phone") String phone) {
		return customerService.findByPhone(phone);
	}

	// update customer by id
	@PutMapping("/{id}")
	public Customer updateCustomer(@PathVariable("id") Integer id, @RequestBody @Validated Customer customer) {
		customerService.findById(id);
		return customerService.save(customer);
	}
	//sort name of customer
//	@GetMapping("/sort")
//	public List<Customer> sort() {
//		return customerService.sortFirstName();
//	}

	// delete customer by id
	@DeleteMapping("/{id}")
	public void deleteCustomerId(@PathVariable("id") Integer id) {
		Customer Customer = customerService.findById(id);
		customerService.deleteById(Customer.getId());
	}

	// add customer
	@PostMapping("")
	public ResponseEntity<?> createCustomer(@RequestBody @Validated Customer customer) {
		Customer savedCustomer = customerService.save(customer);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(savedCustomer, responseHeaders, HttpStatus.CREATED);
	}

}
