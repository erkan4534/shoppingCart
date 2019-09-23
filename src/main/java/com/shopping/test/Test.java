package com.shopping.test;

import java.util.ArrayList;
import java.util.List;

import com.shopping.cart.ShoppingCart;
import com.shopping.compaign.Campaign;
import com.shopping.compaign.CampaignFactor;
import com.shopping.coupon.Coupon;
import com.shopping.coupon.CouponFactory;
import com.shopping.delivery.DeliveryCostCalculator;
import com.shopping.model.Category;
import com.shopping.model.DiscountType;
import com.shopping.model.Product;

public class Test {
  
	public static void main(String[] args) {
		
		
		Category category = new Category("food");		
		Product apple = new Product("Apple", 100.0, category);		
		Product almond = new Product("Almond", 150.0, category);
		
		
		Category category2 = new Category("sebze");		
		Product bezelye = new Product("bezelye", 100.0, category2);		
		Product ispanak = new Product("ispanak", 150.0, category2);
		
		                                    //costPerDelivery, costPerProduct, fixedCost
		ShoppingCart cart = new ShoppingCart(new DeliveryCostCalculator(2, 2, 2.99));	
		cart.addItem(apple, 3);
		cart.addItem(almond, 1);
		
		cart.addItem(bezelye, 3);
		cart.addItem(ispanak, 1);
		
		List<Campaign> campaignList = new ArrayList<Campaign>();
		
		Campaign campaign1 = CampaignFactor.createCompaign(category,20.0,3,DiscountType.Rate);	
		campaignList.add(campaign1);
		
		Campaign campaign2 = CampaignFactor.createCompaign(category2,20.0,3,DiscountType.Rate);	
		campaignList.add(campaign2);
		
			
//		Campaign campaign2 = CampaignFactor.createCompaign(category,50.0,3,DiscountType.Rate);		
//		campaignList.add(campaign2);
//		
//		Campaign campaign3 = CampaignFactor.createCompaign(category,5.0,5,DiscountType.Amount);	
//		campaignList.add(campaign3);
		
		cart.applyDiscounts(campaignList);	
		
		Coupon coupon = CouponFactory.createCoupon(100,10, DiscountType.Rate);
		
		
		List<Coupon> couponList = new ArrayList<Coupon>();
		couponList.add(coupon);
		
		cart.applyCoupon(couponList);
		
		cart.print();


	}
	
}
