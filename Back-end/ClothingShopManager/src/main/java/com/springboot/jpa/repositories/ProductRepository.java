package com.springboot.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.jpa.models.Gender;
import com.springboot.jpa.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("select p from Product p where p.code like %?1%")
	List<Product> findByCode(String code);
	
	@Query("select p from Product p where p.name like %?1%")
	List<Product> findByName(String name);
	
	@Query("select p from Product p where p.price > :min and p.price < :max")
	List<Product> findbyPriceMinMax(Double min , Double max);

	@Query("select p from Product p where p.type.id = :typeId")
	List<Product> findProductByTypeId(Integer typeId);
	
	@Query("select p from Product p where p.size.id = :sizeId")
	List<Product> findProductBySize(Integer sizeId);
	
	@Query("select p from Product p where p.color.id = :colorId")
	List<Product> findProductByColor(Integer colorId);
	
	@Query("select p from Product p where p.gender = :number")
	List<Product> findProductByGender(Integer number);
	
	@Query("select p from Product p where p.type.id = :typeId")
	List<Product> findProductByType(Integer typeId);
	
	@Query("select p from Product p where p.gender = :gender")
	List<Product> getGender(Gender gender);
	
//	@Query("select p from Product p where p.gender = :gender and p.type.id = :typeId")
//	List<Product> getGenderAndType(Gender gender, Integer typeId);
	
	@Query("select p from Product p where p.size.id = :sizeId")
	List<Product> getSize(Integer sizeId);
	
//	@Query("select product from Product product")
//	List<Product> findAlls(Pageable pb);
	
}
