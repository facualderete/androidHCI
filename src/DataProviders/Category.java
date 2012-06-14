package DataProviders;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;


@Retention(RetentionPolicy.RUNTIME)
@interface WhatCategory {
  String description();
}

@WhatCategory(description = "LayoutClass")
public class Category implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@WhatCategory(description = "LayoutElement")
	public Integer id;
	
	@WhatCategory(description = "LayoutElement")
	public String code;
	
	@WhatCategory(description = "LayoutElement")
	public String name;
	
	private java.util.List<subCategory> subCategories;


	public Category() {
		this.id = 1;
		this.name = "DefaultPrueba";

	}


	public Category(int id, String code, String name) {

		this.id=id;
		this.setCode(code);
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	public String toString(){
		return name;
	}
	public Integer getId() {
		return id;
	}
	public List<subCategory> getSubCategories(){
		return subCategories;
	}
	public void addSubCategory(List<subCategory> subcategories) {
		this.subCategories=subcategories;
		
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getCode() {
		return code;
	}

}




	

	


