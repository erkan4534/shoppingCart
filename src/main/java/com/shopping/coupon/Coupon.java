package com.shopping.coupon;

public interface Coupon {

	boolean isCheckDiscount(double minimumAmount);
	
	double getDiscountAmount(double totalCartPrice);
	
}
