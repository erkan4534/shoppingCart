package com.shopping.compaign;

import com.shopping.model.Category;

public class CampaignAmount implements Campaign {

	private double amount;
	private int minPorductQuantity;
	private Category category;

	
	public CampaignAmount(double amount, int minPorductQuantity,Category category) {
		this.amount = amount;
		this.minPorductQuantity = minPorductQuantity;
		this.category=category;
	}
	
	@Override
	public boolean isCheckDiscount(int totalPorductQuantity) {
		return totalPorductQuantity>=minPorductQuantity;
	}

	@Override
	public double getDiscountAmount(double totalCartPrice) {
		return amount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public int getMinPorductQuantity() {
		return minPorductQuantity;
	}
	
	public void setMinPorductQuantity(int minPorductQuantity) {
		this.minPorductQuantity = minPorductQuantity;
	}

    public Category getCategory() {
		return category;
	}
    
    public void setCategory(Category category) {
		this.category = category;
	}

}
