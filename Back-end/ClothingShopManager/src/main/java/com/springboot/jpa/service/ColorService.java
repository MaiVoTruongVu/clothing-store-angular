package com.springboot.jpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jpa.models.Color;
import com.springboot.jpa.repositories.ColorRepository;

@Service
@Transactional
public class ColorService {
	@Autowired
	private ColorRepository colorRepo;
	
	public Color save(Color color) {
		return this.colorRepo.save(color);
	}
	
	public List<Color> listAll() {
		return this.colorRepo.findAll();
	}
	public Color findById(Integer id) {
		return this.colorRepo.findById(id).get();
	}
	
	public void deleteById(Integer id) {
		this.colorRepo.deleteById(id);
	}

}
