package com.springboot.jpa.models;

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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 50, nullable = false, unique = true)
	private String code;
	@Column(length = 100, nullable = false)
	private String name;
	@Column(length = 200)
	private String description;
	@Column(length = 200)
	private String avatar;
	@Column(nullable = false)
	private Double price;
	private Boolean isHot;
	@Column(length = 1, nullable = false)
	private Gender gender;

	@ManyToOne
	@JoinColumn
	private Type type;

	@ManyToOne
	@JoinColumn
	private Producer producer;

	@JsonBackReference
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Image> images = new ArrayList<Image>();

	@ManyToOne
	@JoinColumn
	private Size size;

	@ManyToOne
	@JoinColumn
	private Color color;

	@JsonBackReference
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Detail> details = new ArrayList<Detail>();

	public Product(Integer id, String code, String name, String description, Double price,
			Boolean isHot, Type type, Producer producer, List<Image> images, Size size, Color color,
			List<Detail> details, String avatar, Gender gender) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.price = price;
		this.isHot = isHot;
		this.type = type;
		this.producer = producer;
		this.images = images;
		this.size = size;
		this.color = color;
		this.details = details;
		this.avatar = avatar;
		this.gender = gender;
	}

	public Product() {
		super();
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}

}
