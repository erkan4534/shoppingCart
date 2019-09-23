package com.shopping.model;

public class Product extends BaseEntity {
	
	private double price;
	private Category category;
	
	public Product(String title,double price, Category category) {
		this.title=title;
		this.price = price;
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
