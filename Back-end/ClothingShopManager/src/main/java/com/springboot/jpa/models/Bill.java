package com.springboot.jpa.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "bills")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
//	@Temporal(Temporal)
	private LocalDateTime createdDate;
	@Column(length = 200)
	private String deliveryAddress;
	@Column(length = 100,nullable = true ,unique = true)
	private String code;

	@JsonBackReference
	@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
	private List<Detail> details = new ArrayList<Detail>();

	@ManyToOne
	@JoinColumn
	private Customer customer;

	public Bill(Integer id, LocalDateTime createdDate, String deliveryAddress, String code, List<Detail> details,
			Customer customer) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.deliveryAddress = deliveryAddress;
		this.code = code;
		this.details = details;
		this.customer = customer;
	}

	public Bill() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	
}
