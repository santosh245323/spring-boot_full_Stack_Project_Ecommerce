package com.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 500)
	private String title;

	@Column(length = 5000)
	private String description;

	private Double price;

	private int stock;

	private String image;

	private int discount;

	private Double discountPrice;

	private Boolean enabled;

	@ManyToOne
	@JoinColumn(name="seller_id",nullable = false)
	private UserDTO seller;
	

}
