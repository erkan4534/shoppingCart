package com.shopping.delivery;

import com.shopping.cart.ShoppingCart;

public interface DeliveryStrategy {
   
	double calculateFor(ShoppingCart cart);
}
