package com.springboot.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.jpa.models.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer>{

}
