package com.springboot.jpa.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jpa.models.Bill;
import com.springboot.jpa.models.Detail;
import com.springboot.jpa.repositories.BillRepository;

@Service
@Transactional
public class BillService {
	@Autowired
	private BillRepository billRepository;
	
	public Bill save(Bill bill) {
		return this.billRepository.save(bill);
	}
	
	public List<Bill> listAll() {
		return this.billRepository.findAll();
	}
	
	public Bill findById(Integer id) {
		return this.billRepository.findById(id).get();
	}
	
	public void deleteById(Integer id) {
		this.billRepository.deleteById(id);
	}
	
	public List<Bill> findByDate(Date createdDate) {
		return this.billRepository.findByCreatedDate(createdDate);
	}
	
	public List<Bill> findByCustomer(Integer customerId) {
		return this.billRepository.findByCustomer(customerId);
	}
	
	public List<Bill> billNow(LocalDateTime startDate, LocalDateTime endDate){
		return this.billRepository.billNow(startDate, endDate);
	}
	
	public List<Bill> billDate(LocalDateTime startDate, LocalDateTime endDate){
		return this.billRepository.billDate(startDate, endDate);
	}
	
	public List<Bill> listStatistic(LocalDateTime startDate, LocalDateTime endDate, String firstName){
		if(firstName.equals("abc")) {
			return this.billRepository.billDate(startDate, endDate);
		} else {
		return this.billRepository.listBillDateAndFirstName(startDate, endDate, firstName);
		}
	}
	
	public List<Bill> dateNow(LocalDateTime startDate, LocalDateTime endDate, String firstName){
		if(firstName.equals("abc")) {
			return this.billRepository.billNow(startDate, endDate);
		} else {
		return this.billRepository.billNowAndFirstName(startDate, endDate, firstName);
		}
	}


}
