package com.springboot.jpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jpa.models.Banner;
import com.springboot.jpa.repositories.BannerRepository;

@Service
@Transactional
public class BannerService {
	@Autowired
	private BannerRepository bannerRepository;
	
	public Banner save(Banner banner) {
		return this.bannerRepository.save(banner);
	}
	
	public List<Banner> listAll() {
		return this.bannerRepository.findAll();
	}
	
	public Banner findById(Integer id) {
		return this.bannerRepository.findById(id).get();
	}
	
	public void deleteById(Integer id) {
		this.bannerRepository.deleteById(id);
	}

}
