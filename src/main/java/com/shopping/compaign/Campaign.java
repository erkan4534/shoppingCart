package com.shopping.compaign;

import com.shopping.model.Category;

public interface Campaign {
  
	double getDiscountAmount(double totalCartPrice);
	
	public boolean isCheckDiscount(int totalPorductQuantity);
	
	Category getCategory();
		
}
