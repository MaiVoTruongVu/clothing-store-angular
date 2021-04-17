package com.springboot.jpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jpa.models.Size;
import com.springboot.jpa.repositories.SizeRepository;

@Service
@Transactional
public class SizeService {
	@Autowired
	private SizeRepository sizeRepo;
	
	public Size save(Size size) {
		return this.sizeRepo.save(size);
	}
	
	public List<Size> listAll() {
		return this.sizeRepo.findAll();
	}
	public Size findById(Integer id) {
		return this.sizeRepo.findById(id).get();
	}
	
	public void deleteById(Integer id) {
		this.sizeRepo.deleteById(id);
	}

}
