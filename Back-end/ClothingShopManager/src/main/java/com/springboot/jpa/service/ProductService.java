package com.springboot.jpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jpa.models.Gender;
import com.springboot.jpa.models.Product;
import com.springboot.jpa.repositories.ProductRepository;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product Product) {
		return this.productRepository.save(Product);
	}
	
	public List<Product> listAll() {
		return this.productRepository.findAll();
	}
	public List<Product> listAllNotPage() {
		return this.productRepository.findAll();
	}
	
	public Product findById(Integer id) {
		return this.productRepository.findById(id).get();
	}
	
	public void deleteById(Integer id) {
		this.productRepository.deleteById(id);
	}
	
	public List<Product> findByCode(String code) {
		return this.productRepository.findByCode(code);
	}
	
	public List<Product> findByName(String name) {
		return this.productRepository.findByName(name);
	}
	
	public List<Product> findbyPriceMinMax(Double min , Double max) {
		return this.productRepository.findbyPriceMinMax(min, max);
	}
	
	public List<Product> findProductBySize(Integer sizeId){
		return this.productRepository.findProductBySize(sizeId);
	}
	
	public List<Product> findProductByColor(Integer colorId){
		return this.productRepository.findProductByColor(colorId);
	}
	
	public List<Product> findProductByGender(Integer number){
		return this.productRepository.findProductByGender(number);
	}
	
	public List<Product> findProductByType(Integer typeId){
		return this.productRepository.findProductByType(typeId);
	}

	public List<Product> getGender(Gender gender){
		return this.productRepository.getGender(gender);
	}
//	public List<Product> getGenderAndType(Gender gender , Integer typeId){
//		return this.productRepository.getGenderAndType(gender , typeId);
//	}
	
	public List<Product> getSize(Integer sizeId){
		return this.productRepository.getSize(sizeId);
	}
	
}
