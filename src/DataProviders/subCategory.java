package DataProviders;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@interface WhatsubCategory {
  String description();
}

@WhatsubCategory(description = "LayoutClass")
public class subCategory implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@WhatsubCategory(description = "LayoutElement")
	public String code;
	
	@WhatsubCategory(description = "LayoutElement")
	public String name;
	
	@WhatsubCategory(description = "LayoutElement")
	public int category;
	
	@WhatsubCategory(description = "LayoutElement")
	public int id;

	public subCategory(int id, String code, String name, int category) {
		this.category=category;
		this.code=code;
		this.id=id;
		this.name=name;
	}

	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCateg() {
		return category;
	}

	public void setCateg(int categ) {
		this.category = categ;
	}
	
}
