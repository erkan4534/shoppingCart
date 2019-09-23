package com.shopping.compaign;

import com.shopping.model.Category;

public class CampaignRate implements Campaign {

	private double rate;
	private int minPorductQuantity;
	private Category category;

	public CampaignRate(double rate, int minPorductQuantity,Category category) {
		this.rate = rate;
		this.minPorductQuantity = minPorductQuantity;
		this.category=category;
	}
	
	@Override
	public boolean isCheckDiscount(int totalPorductQuantity) {
		return totalPorductQuantity>=minPorductQuantity;
	}

	@Override
	public double getDiscountAmount(double totalCartPrice) {
		return  totalCartPrice * (rate * 0.01);
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
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
