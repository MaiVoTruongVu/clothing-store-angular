package com.springboot.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.jpa.models.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer>{

}
