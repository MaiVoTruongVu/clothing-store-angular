package com.springboot.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.jpa.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>{
	@Query("select img from Image img where img.product.id = :id")
	List<Image> getProductById(Integer id);

}
