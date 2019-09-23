package com.shopping.coupon;

public class CouponAmount implements Coupon {

	private double amount;
	private double minimumAmount;

	public CouponAmount(double minimumAmount, double amount) {
		this.amount = amount;
		this.minimumAmount = minimumAmount;
	}

	@Override
	public boolean isCheckDiscount(double totalCartPrice) {
		return totalCartPrice >= this.minimumAmount;
	}

	@Override
	public double getDiscountAmount(double totalCartPrice) {
		return this.amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getMinimumAmount() {
		return minimumAmount;
	}

	public void setMinimumAmount(double minimumAmount) {
		this.minimumAmount = minimumAmount;
	}

}
