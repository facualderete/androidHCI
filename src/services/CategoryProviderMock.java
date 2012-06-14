package services;

import java.util.ArrayList;
import java.util.List;

import DataProviders.Category;

public class CategoryProviderMock extends AbstractCategoryProvider {

	@Override
	public List<Category> getCategories() {
		List<Category> cats = new ArrayList<Category>();

		cats.add(new Category(1,"1","DVD"));
		cats.add(new Category(2,"2","Books"));

		return cats;
	
	}



}
