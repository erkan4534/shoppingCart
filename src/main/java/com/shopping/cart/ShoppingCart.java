package com.shopping.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.shopping.compaign.Campaign;
import com.shopping.coupon.Coupon;
import com.shopping.delivery.DeliveryStrategy;
import com.shopping.model.Category;
import com.shopping.model.Product;

public class ShoppingCart {

	/*
	 * product'larin sayilarinin tutuldugu map
	 */
	private Map<Product, Integer> addItem;

	double totalCampaignDiscount;
	double totalCouponDiscount;
	double totalCartPrice;
	double totalAmountAfterDiscountResult;
	double totalAmountAfterCouponResult;
	double totalAmountAfterDeliveryResult;
	private DeliveryStrategy deliveryStrategy;
	private Map<Category, List<Product>> productToCategoryMap;
	private List<Product> productList ; 

	public ShoppingCart(DeliveryStrategy deliveryStrategy) {
		this.deliveryStrategy = deliveryStrategy;
		this.addItem = new HashMap<Product, Integer>();
		this.productToCategoryMap = new HashMap<Category, List<Product>>();
		this.productList=new ArrayList<Product>();
	}


	public void addItem(Product product, Integer quantity) {
		
		
		if (quantity <= 0) {
			throw new RuntimeException("quantity 0 veya negatif bir deger alamaz");
		}

		if (product == null) {
			throw new RuntimeException("product null olamaz");
		}
		

		if (!addItem.containsKey(product)) {
						
			addItem.put(product, quantity);
				
		} else {
			addItem.put(product, addItem.get(product) + quantity);
		}

		fillProductToCategoryMap(product);
		
	}


	private void fillProductToCategoryMap(Product product) {
		
		if(!productToCategoryMap.containsKey(product.getCategory())) {
			
			productList = new ArrayList<Product>();
			productList.add(product);
			productToCategoryMap.put(product.getCategory(), productList);
			
		}else {
	
			productToCategoryMap.get(product.getCategory()).add(product);
			productToCategoryMap.put(product.getCategory(), productList);	
		}
		
	}

	public void applyDiscounts(List<Campaign> campaignList) {
		totalAmountAfterDiscountResult = getTotalAmountAfterDiscount(campaignList);
	}

	public void applyCoupon(List<Coupon> couponList) {

		totalAmountAfterCouponResult = totalAmountAfterDiscountResult
				- getCouponDiscount(couponList, totalAmountAfterDiscountResult);
	}

	public double getCouponDiscount(List<Coupon> couponList, double totalAmountAfterDiscount) {

		return couponList.stream().filter(k->k.isCheckDiscount(totalAmountAfterDiscount)).mapToDouble(c -> c.getDiscountAmount(totalAmountAfterDiscount)).sum();
	}

	public double getTatalCartPrice(Map<Product, Integer> addItem) {

		totalCartPrice = 0;

		addItem.forEach((k, v) -> {
			totalCartPrice += k.getPrice() * addItem.get(k);
		});

		return totalCartPrice;
	}

	public double getCampaignDiscount(List<Campaign> campaignList) {

		totalCampaignDiscount = 0;

		addItem.forEach((k, v) -> { // urunlerin bagli oldugu kategoriler

			campaignList.stream().forEach(c -> { // campanyalarin bagli oldugu categoriler

				if (k.getCategory().equals(c.getCategory())) {

					if (c.isCheckDiscount(addItem.get(k))) {

						totalCampaignDiscount += c.getDiscountAmount(this.totalCartPrice);

					}

				}

			});

		});

		return totalCampaignDiscount;
	}

	public double getTotalAmountAfterDiscount(List<Campaign> campaignList) {

		return getTatalCartPrice(addItem) - getCampaignDiscount(campaignList);
	}

	public int getNumberOfProducts() {

		return addItem.keySet().size();
	}

	public double getNumberOfDeliveries() {

		return addItem.keySet().stream().map(c -> c.getCategory()).distinct().count();
	}

	public double getDeliveryCost() {

		return deliveryStrategy.calculateFor(this);
	}
	
	public void print() {

		StringBuilder str = new StringBuilder();
		
		productToCategoryMap.forEach((k, v) -> {
        
			str.append("Category : ");
			str.append(k.getTitle());
			str.append("\n\r");
			
			v.forEach(product->{
				
				str.append("ProductName : ");
				str.append(product.getTitle());
				str.append(" , ");
				str.append(" Quantity   : ");
				str.append(v.size());
				str.append(" , ");
				str.append(" Unit-Price : ");
				str.append(product.getPrice());
				str.append("\n\r");
				
			});
		});
		
		str.append("--------------------------------------------------------------------");
		str.append("\n\r");
		
		str.append("Total Price    : ");
		str.append(totalCartPrice);
		str.append("\n\r");
		
		str.append("Total Discount : ");
		str.append(totalAmountAfterCouponResult);
		str.append("\n\r");
		
		str.append("Delivery Cost  : ");
		str.append(getDeliveryCost());
		str.append("\n\r");
		
		
		System.out.println(str);
		
	}
	
	
	public Map<Product, Integer> getAddItem() {
		return addItem;
	}
	
	
	public void setAddItem(Map<Product, Integer> addItem) {
		this.addItem = addItem;
	} 

}
