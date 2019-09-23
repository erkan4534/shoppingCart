package com.shopping.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.shopping.cart.ShoppingCart;
import com.shopping.compaign.Campaign;
import com.shopping.compaign.CampaignFactor;
import com.shopping.coupon.Coupon;
import com.shopping.coupon.CouponFactory;
import com.shopping.delivery.DeliveryCostCalculator;
import com.shopping.model.Category;
import com.shopping.model.DiscountType;
import com.shopping.model.Product;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ShoppingCartTest {
	
	Category category;
	Product apple;
	Product almond;
	ShoppingCart cart;
	List<Campaign> campaignList;
	List<Coupon> couponList;
	
    @Before
	public  void setUp() {
		
    	
    	category = new Category("food");		
		apple = new Product("Apple", 100.0, category);		
		almond = new Product("Almond", 150.0, category);
		
		                                    //costPerDelivery, costPerProduct, fixedCost
		cart = new ShoppingCart(new DeliveryCostCalculator(2, 2, 2.99));	
		cart.addItem(apple, 3);
		cart.addItem(almond, 1);
		
		campaignList = new ArrayList<Campaign>();
		
		Campaign campaign1 = CampaignFactor.createCompaign(category,20.0,3,DiscountType.Rate);	
		campaignList.add(campaign1);
			
//		Campaign campaign2 = CampaignFactor.createCompaign(category,50.0,3,DiscountType.Rate);		
//		campaignList.add(campaign2);
//		
//		Campaign campaign3 = CampaignFactor.createCompaign(category,5.0,5,DiscountType.Amount);	
//		campaignList.add(campaign3);
		
		cart.applyDiscounts(campaignList);	
		
		Coupon coupon = CouponFactory.createCoupon(100,10, DiscountType.Rate);
		
		
		couponList = new ArrayList<Coupon>();
		couponList.add(coupon);
		
		cart.applyCoupon(couponList);
		
		cart.print();

	}
    
    @Test
    public void getTatalCartPrice() {
    	Assert.assertEquals(450.0, cart.getTatalCartPrice(cart.getAddItem()));   
    }
	
    @Test
    public void  getCampaignDiscount() {
    	Assert.assertEquals(90.0, cart.getCampaignDiscount(campaignList));   
    }
    
    @Test
    public void getCouponDiscount() {
    	Assert.assertEquals(36.0, cart.getCouponDiscount(couponList, cart.getTotalAmountAfterDiscount(campaignList)));  
    }
    
    @Test
    public void getTotalAmountAfterDiscount() {
    	Assert.assertEquals(360.0, cart.getTotalAmountAfterDiscount(campaignList)); 
    }
    
    @Test
    public void getNumberOfProducts() {
    	Assert.assertEquals(2, cart.getNumberOfProducts()); 
    }
    
    @Test
    public void getNumberOfDeliveries() {
    	Assert.assertEquals(1.0, cart.getNumberOfDeliveries()); 
    }
    
    @Test
    public void getDeliveryCost() {
    	Assert.assertEquals(8.99, cart.getDeliveryCost()); 
    }
    
    @Test(expected=RuntimeException.class)
    public void productAdd() {
    	cart.addItem(null, 10);
    }
    
    @Test(expected=RuntimeException.class)
    public void productQuantityAdd() {    	
    	Category category = new Category("food");		
    	Product product = new Product("Apple", 100.0, category);   	
    	cart.addItem(product, null);
    }
    
    
    
}
