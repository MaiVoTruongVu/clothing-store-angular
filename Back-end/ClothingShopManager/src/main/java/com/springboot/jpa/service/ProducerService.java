package com.springboot.jpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jpa.models.Producer;
import com.springboot.jpa.repositories.ProducerRepository;

@Service
@Transactional
public class ProducerService {
	@Autowired
	private ProducerRepository producerRepository;
	
	public Producer save(Producer Producer) {
		return this.producerRepository.save(Producer);
	}
	
	public List<Producer> listAll() {
		return this.producerRepository.findAll();
	}
	
	public Producer findById(Integer id) {
		return this.producerRepository.findById(id).get();
	}
	
	public void deleteById(Integer id) {
		this.producerRepository.deleteById(id);
	}
	
	public List<Producer> findByName(String name) {
		return this.producerRepository.findByName(name);
	}

}
