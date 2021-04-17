package com.springboot.jpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jpa.models.Type;
import com.springboot.jpa.repositories.TypeRepository;

@Service
@Transactional
public class TypeService {
	@Autowired
	private TypeRepository typeRepository;
	
	public Type save(Type Type) {
		return this.typeRepository.save(Type);
	}
	
	public List<Type> listAll() {
		return this.typeRepository.findAll();
	}
//	public List<Type> saveAll(List<Type> list) {
//		return list.forEach(s -> typeRepository.save(s));
//	}
	public Type findById(Integer id) {
		return this.typeRepository.findById(id).get();
	}
	
	public void deleteById(Integer id) {
		this.typeRepository.deleteById(id);
	}

}
