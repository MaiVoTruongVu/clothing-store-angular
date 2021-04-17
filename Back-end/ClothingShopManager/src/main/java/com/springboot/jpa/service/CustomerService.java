package com.springboot.jpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jpa.models.Customer;
import com.springboot.jpa.repositories.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer save(Customer customer) {
		return this.customerRepository.save(customer);
	}
	
	public List<Customer> listAll() {
		return this.customerRepository.findAll();
	}
	
	public Customer findById(Integer id) {
		return this.customerRepository.findById(id).get();
	}
	
	public void deleteById(Integer id) {
		this.customerRepository.deleteById(id);
	}
	
	public List<Customer> findbyFirstName(String firstName) {
		return this.customerRepository.findByFirstName(firstName);
	}
	
	public Customer findByFullName(String lastName , String firstName) {
		return this.customerRepository.findByFullName(lastName, firstName);
	}
	
	public Customer findByPhone(String phone) {
		return this.customerRepository.findByPhone(phone);
	}
	
//	public List<Customer> sortFirstName() {
//		return this.customerRepository.sortfirstName();
//	}

}
