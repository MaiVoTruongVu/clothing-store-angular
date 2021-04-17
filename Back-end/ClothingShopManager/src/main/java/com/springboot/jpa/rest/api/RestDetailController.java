package com.springboot.jpa.rest.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.springboot.jpa.models.Bill;
import com.springboot.jpa.models.Detail;
import com.springboot.jpa.models.Product;
import com.springboot.jpa.service.BillService;
import com.springboot.jpa.service.DetailService;
import com.springboot.jpa.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/details")
@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
public class RestDetailController {
	@Autowired
	private DetailService detailService;

	@Autowired
	private ProductService productService;

	@Autowired
	private BillService billService;
	
//	@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
	@GetMapping("")
	public List<Detail> getAll() {
		return this.detailService.listAll();
	}
//	@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
	@GetMapping("/statistic/{startDate}/{endDate}/cus/{firstName}")
	public List<Detail> listStatistic(
			@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
			@PathVariable("firstName") String firstName) {
		
		return this.detailService.listStatistic(startDate.atTime(0, 0, 0), endDate.atTime(23, 59, 59), firstName);
	}
//	@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
	@GetMapping("/dateNow/cus/{firstName}")
	public List<Detail> dateNow(@PathVariable("firstName") String firstName) {
		LocalDate bn = LocalDate.now();
		return this.detailService.listStatistic(bn.atTime(0, 0, 0), bn.atTime(23, 59, 59), firstName);
	}

	// get id detail
//	@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
	@GetMapping("/{id}")
	public Detail getId(@PathVariable("id") Integer id) {
		return detailService.findById(id);
	}

	// add detail
	@PreAuthorize("permitAll()")
	@PostMapping(value = "", consumes = "multipart/form-data")
	public Detail saveDetail(@RequestParam("quantity") Integer quantity, @RequestParam("price") Double price,
			@RequestParam("product") Integer productId, @RequestParam("bill") Integer billId) {
		Product product = productService.findById(productId);
		Bill bill = billService.findById(billId);
		Detail detail = new Detail();
		detail.setBill(bill);
		detail.setProduct(product);
		detail.setPrice(price);
		detail.setQuantity(quantity);
		return detailService.save(detail);
	}

	// delete detail
//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteDetailId(@PathVariable("id") Integer id) {
		Detail detail = detailService.findById(id);
		detailService.deleteById(detail.getId());
	}

	// update detail
//	@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
	@PutMapping(value = "", consumes = "multipart/form-data")
	public Detail updateDetail(@RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity,
			@RequestParam("price") Double price, @RequestParam("product") Integer productId,
			@RequestParam("bill") Integer billId) {
		Product product = productService.findById(productId);
		Bill bill = billService.findById(billId);
		Detail detail = detailService.findById(id);
		detail.setBill(bill);
		detail.setProduct(product);
		detail.setPrice(price);
		detail.setQuantity(quantity);
		return detailService.save(detail);
	}

	// find by Bill id
//	@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
	@GetMapping("/findBillId/{billId}")
	public List<Detail> findByBillId(@PathVariable("billId") Integer billId) {
		return this.detailService.findByBillId(billId);
	}

}
