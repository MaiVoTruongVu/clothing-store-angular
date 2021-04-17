package com.springboot.jpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jpa.models.Image;
import com.springboot.jpa.repositories.ImageRepository;

@Service
@Transactional
public class ImageService {
	@Autowired
	private ImageRepository imageRepository;
	
	public Image save(Image Image) {
		return this.imageRepository.save(Image);
	}
	
	public List<Image> listAll() {
		return this.imageRepository.findAll();
	}
	
	public Image findById(Integer id) {
		return this.imageRepository.findById(id).get();
	}
	
	public void deleteById(Integer id) {
		this.imageRepository.deleteById(id);
	}
	
	public List<Image> getProductById(Integer id){
		return this.imageRepository.getProductById(id);
	}

}
