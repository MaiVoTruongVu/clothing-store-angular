package com.springboot.jpa.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jpa.models.Detail;
import com.springboot.jpa.repositories.DetailRepository;

@Service
@Transactional
public class DetailService {
	@Autowired
	private DetailRepository detailRepo;

	public Detail save(Detail detail) {
		return this.detailRepo.save(detail);
	}

	public List<Detail> listAll() {
		return this.detailRepo.findAll();
	}

	public Detail findById(Integer id) {
		return this.detailRepo.findById(id).get();
	}

	public void deleteById(Integer id) {
		this.detailRepo.deleteById(id);
	}
	
	public List<Detail> findByBillId(Integer billId) {
		return this.detailRepo.findByBillId(billId);
	}
	
	public List<Detail> listStatistic(LocalDateTime startDate, LocalDateTime endDate, String firstName){
		if(firstName.equals("abc")) {
			return this.detailRepo.listStatistic(startDate, endDate);
		} else {
		return this.detailRepo.listStatisticAndFirstName(startDate, endDate, firstName);
		}
	}
	
	public List<Detail> dateNow(LocalDateTime startDate, LocalDateTime endDate, String firstName){
		if(firstName.equals("abc")) {
			return this.detailRepo.dateNow(startDate, endDate);
		} else {
		return this.detailRepo.dateNowAndFirstName(startDate, endDate, firstName);
		}
	}

}
