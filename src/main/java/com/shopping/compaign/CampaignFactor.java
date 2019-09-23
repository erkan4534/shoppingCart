package com.shopping.compaign;

import com.shopping.model.Category;
import com.shopping.model.DiscountType;

public class CampaignFactor {

	public static Campaign createCompaign(Category category, double discountValue, int minPorductQuantity,
			DiscountType type) {

		Campaign compaign = null;

		switch (type) {

		case Amount:

			compaign = new CampaignAmount(discountValue, minPorductQuantity,category);
			break;

		case Rate:

			compaign = new CampaignRate(discountValue, minPorductQuantity,category);
			break;

		default:
			break;
		}

		return compaign;
	}

}
