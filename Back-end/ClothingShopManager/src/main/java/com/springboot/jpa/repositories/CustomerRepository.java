package com.springboot.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.jpa.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	@Query("select cus from Customer cus where cus.firstName like %?1%")
	List<Customer> findByFirstName(String firstName);
	
	@Query("select cus from Customer cus where cus.lastName like %?1% and cus.firstName  like %?2% ")
	Customer findByFullName(String lastName, String firstName);
	
	@Query("select cus from Customer cus where cus.phone = :phone")
	Customer findByPhone(String phone);
	
//	@Query("select cus from Customer cus order by firstName")
//	List<Customer> sortfirstName();

}
