package com.springboot.jpa.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.jpa.models.Detail;

public interface DetailRepository extends JpaRepository<Detail, Integer>{
	@Query("select d from Detail d where d.bill.id = :billId")
	List<Detail> findByBillId(Integer billId);
	
	@Query("select d from Detail d where (d.bill.createdDate between :startDate and :endDate) and d.bill.customer.firstName like %:firstName%")
	List<Detail> listStatisticAndFirstName(LocalDateTime startDate , LocalDateTime endDate, String firstName);
	
	@Query("select d from Detail d where (d.bill.createdDate between :startDate and :endDate) and d.bill.customer.firstName like %:firstName%")
	List<Detail> dateNowAndFirstName(LocalDateTime startDate , LocalDateTime endDate, String firstName);
	
	@Query("select d from Detail d where d.bill.createdDate between :startDate and :endDate")
	List<Detail> dateNow(LocalDateTime startDate , LocalDateTime endDate);
	
	@Query("select d from Detail d where d.bill.createdDate between :startDate and :endDate")
	List<Detail> listStatistic(LocalDateTime startDate , LocalDateTime endDate);
	
	

}
