package com.springboot.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpa.models.Size;

public interface SizeRepository extends JpaRepository<Size, Integer>{

}
