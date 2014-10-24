package com.example.lap_2_2;

public class Product {
	
	String name;
	double price;
	
	public Product(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString() {
		return this.name;
	}
	
	

}
