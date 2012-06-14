package classes;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

@Retention(RetentionPolicy.RUNTIME)
@interface WhatBookItem {
  
}

@WhatBookItem
public class BookItem extends Item implements Serializable {


	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@WhatBookItem
	public Date publication_date;
	
	@WhatBookItem
	public String isbn10;
	
	@WhatBookItem
	public String isbn13;
	
	@WhatBookItem
	public String language;
	
	@WhatBookItem
	public String authors;
	
	@WhatBookItem
	public String publisher;

	public BookItem(int id, int sales, String n, int categ, int scateg,
			double price, String imgUrl) {
		super(id, n, sales, scateg, categ, imgUrl, price);
	}

	public String getInfo() {
		return "authors: " + authors + "Publisher :" + publisher;
	}

	public void addInfo(String authors, String publisher, Date publication_date,
			String isbn10, String isbn13, String language) {

		this.publication_date = publication_date;
		this.language = language;
		this.isbn10 = isbn10;
		this.isbn13 = isbn13;
		this.authors = authors;
		this.publisher = publisher;
	}

	public Date getPub_date() {
		return publication_date;
	}

	public String getISBN_10() {
		return isbn10;
	}

	public String getISBN_13() {
		return isbn13;
	}

	public String getLanguage() {
		return language;
	}

	public String getAuthors() {
		return authors;
	}

	public String getPublisher() {
		return publisher;
	}

}