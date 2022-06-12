package com.nt.entity;

import lombok.Data;

@Data
public final class Product implements IProduct {
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;

	public Product() {
		System.out.println("Product.Product()");
	}

}
