package com.springboot.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpa.models.Color;

public interface ColorRepository extends JpaRepository<Color, Integer>{

}
