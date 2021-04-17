package com.springboot.jpa.repositories;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.jpa.models.Bill;
import com.springboot.jpa.models.Detail;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer>{
	@Query("select b from Bill b where b.createdDate like %?1%")
	List<Bill> findByCreatedDate(Date createdDate);
	
	@Query("select b from Bill b where b.customer.id = :customerId")
	List<Bill> findByCustomer(Integer customerId);
	
	@Query("select b from Bill b where b.createdDate between :startDate and :endDate")
	List<Bill> billNow(LocalDateTime startDate , LocalDateTime endDate);
	
	@Query("select b from Bill b where b.createdDate between :startDate and :endDate")
	List<Bill> billDate(LocalDateTime startDate , LocalDateTime endDate);
	
	@Query("select b from Bill b where (b.createdDate between :startDate and :endDate) and b.customer.firstName like %:firstName%")
	List<Bill> listBillDateAndFirstName(LocalDateTime startDate , LocalDateTime endDate, String firstName);
	
	@Query("select b from Bill b where (b.createdDate between :startDate and :endDate) and b.customer.firstName like %:firstName%")
	List<Bill> billNowAndFirstName(LocalDateTime startDate , LocalDateTime endDate, String firstName);

}
