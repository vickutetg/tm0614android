package com.example.namnh_bcshop;

import java.io.Serializable;

import android.annotation.SuppressLint;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String 	productName;
	private float 	price;
	private String 	str_image;
	
	public Product() {
	}
	
	public Product(String productName, float price, String str_image) {
		super();
		this.productName = productName;
		this.price = price;
		this.str_image = str_image;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getStr_image() {
		return str_image;
	}
	public void setStr_image(String str_image) {
		this.str_image = str_image;
	}

	@SuppressLint("DefaultLocale")
	@Override
	public String toString() {
		return String.format("%s\t\t\t%f\t\t\t%s" , productName, price, str_image);
	}
}
