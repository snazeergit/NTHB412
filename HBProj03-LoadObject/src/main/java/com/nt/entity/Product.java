package com.nt.entity;

import lombok.Data;

@Data
public  class Product {
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;

	public Product() {
		System.out.println("Product :: 0-param Constructor");
	}

}
