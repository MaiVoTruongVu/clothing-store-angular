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
import com.springboot.jpa.models.Customer;
import com.springboot.jpa.models.Detail;
import com.springboot.jpa.service.BillService;
import com.springboot.jpa.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/bills")
@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
public class RestBillController {
	@Autowired
	private BillService billService;

	@Autowired
	private CustomerService cusService;

//	@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
	@GetMapping("")
	public List<Bill> getAll() {
		return this.billService.listAll();
	}

	// get id bill
//	@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
	@GetMapping("/{id}")
	public Bill getId(@PathVariable("id") Integer id) {
		return billService.findById(id);
	}
//	@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
	@GetMapping("/billDate/{startDate}/{endDate}/cus/{firstName}")
	public List<Bill> listBillDate(
			@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
			@PathVariable("firstName") String firstName) {
		
		return this.billService.listStatistic(startDate.atTime(0, 0, 0), endDate.atTime(23, 59, 59), firstName);
	}
//	@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
	@GetMapping("/billNow/cus/{firstName}")
	public List<Bill> billNow(@PathVariable("firstName") String firstName) {
		LocalDate bn = LocalDate.now();
		return this.billService.listStatistic(bn.atTime(0, 0, 0), bn.atTime(23, 59, 59), firstName);
	}
	// update bill
//	@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
	@PutMapping(value = "", consumes = "multipart/form-data")
	public Bill updateBillId(@RequestParam("id") Integer billId,
			@RequestParam("deliveryAddress") String deliveryAddress) {
		Bill bill = billService.findById(billId);
		bill.setDeliveryAddress(deliveryAddress);
		bill = billService.save(bill);
		bill.setCode("MS0"+billId);
		this.billService.save(bill);
		return bill;
	}

	// delete bill
//	@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteBillId(@PathVariable("id") Integer id) {
		Bill bill = billService.findById(id);
		billService.deleteById(bill.getId());
	}

	// add bill
	@PreAuthorize("permitAll()")
	@PostMapping(value = "", consumes = "multipart/form-data")
	public Bill saveBill(@RequestParam("deliveryAddress") String deliveryAddress,
			@RequestParam("customer") Integer customerId) {
		Bill bill = new Bill();
		Customer cus = cusService.findById(customerId);
		bill.setCreatedDate(LocalDateTime.now());
		bill.setDeliveryAddress(deliveryAddress);
		bill.setCustomer(cus);
		bill = billService.save(bill);
		bill.setCode("MS0"+bill.getId());
		this.billService.save(bill);
		return bill;
	}

	// get list bill by customer id
//	@PreAuthorize("hasAnyRole('Accountant', 'ADMIN')")
	@GetMapping("/findByCusId/{customerId}")
	public List<Bill> findByCustomer(@PathVariable("customerId") Integer customerId) {
		return this.billService.findByCustomer(customerId);
	}

}
