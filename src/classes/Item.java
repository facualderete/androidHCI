package classes;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface WhatItem {
  
}

@WhatItem
public abstract class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WhatItem
	public int id;
	
	@WhatItem
	public int subcategory_id;
	
	@WhatItem
	public double price;
	
	@WhatItem
	public String imgUrl;
	
	@WhatItem
	public int sales_rank;
	
	@WhatItem
	public int category_id;
	
	@WhatItem
	public String name;

	public Item(int id, String name, int sales, int subcategory, int category,
			String image, double price) {

		this.id = id;
		this.name = name;
		this.sales_rank = sales;
		this.subcategory_id = subcategory;
		this.category_id = category;
		this.imgUrl = image;
		this.price = price;

	}

	public abstract String getInfo();

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSales_rank() {
		return sales_rank;
	}

	public int getId() {
		return id;
	}

	public int getCateg_id() {
		return category_id;
	}

	public void setCateg_id(int id) {
		this.category_id = id;
	}

	public void setSales_rank(int sales) {
		this.sales_rank = sales;
	}

	public int getScateg_id() {
		return subcategory_id;
	}

	public void setScateg_id(int id) {
		this.subcategory_id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int newPrice) {
		this.price = newPrice;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String url) {
		this.imgUrl = url;
	}

}
