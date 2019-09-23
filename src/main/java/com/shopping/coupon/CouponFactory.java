package com.shopping.coupon;

import com.shopping.model.DiscountType;

public class CouponFactory {

	public static Coupon createCoupon(int minimumAmount, double discountValue, DiscountType type) {

		Coupon coupon = null;

		switch (type) {

		case Amount:

			coupon = new CouponAmount(minimumAmount, discountValue);
			break;

		case Rate:

			coupon = new CouponRate(minimumAmount, discountValue);
			break;

		default:
			break;
		}

		return coupon;
	}
}
