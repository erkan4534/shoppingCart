package com.shopping.coupon;

public class CouponRate implements Coupon {

	private double rate;
	private double minimumAmount;


	public CouponRate(double minimumAmount, double rate) {
		this.rate = rate;
		this.minimumAmount = minimumAmount;
	}

	@Override
	public boolean isCheckDiscount(double totalCartPrice) {
		return totalCartPrice >= this.minimumAmount;
	}

	@Override
	public double getDiscountAmount(double totalCartPrice) {
		return totalCartPrice * (this.rate * 0.01);
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getMinimumAmount() {
		return minimumAmount;
	}

	public void setMinimumAmount(double minimumAmount) {
		this.minimumAmount = minimumAmount;
	}



}
