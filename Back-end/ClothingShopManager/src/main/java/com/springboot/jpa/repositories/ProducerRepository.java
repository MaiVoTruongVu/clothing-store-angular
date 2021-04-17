package com.springboot.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.jpa.models.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer>{
	@Query("select pro from Producer pro where pro.name like %?1%")
	List<Producer> findByName(String name);
	
//	@Query("select pro from Producer pro")
//	List<Producer> findTatCa(Pageable pb);
	
	
	

}
