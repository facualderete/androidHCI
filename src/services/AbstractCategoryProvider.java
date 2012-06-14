package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import DataProviders.CategoriesProvider;
import DataProviders.Category;

public abstract class AbstractCategoryProvider implements CategoriesProvider {

	public static final String[] fields = { "id", "name" };
	
	@Override
	public List<Category> getCategories() {
		
		List<Category> l = new ArrayList<Category>();
		l.add(new Category());
		return l;
	}

	@Override
	public List<? extends Map<String, ?>> getCategoryAsMap() {
		List<Category> categories = getCategories();
		List<Map<String, String>> transformedCategories = new ArrayList<Map<String, String>>();
		for (Category t : categories) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(fields[0], t.getId().toString());
			map.put(fields[1], t.getName());
			transformedCategories.add(map);
		}
		return transformedCategories;
	}

	@Override
	public String[] getMapKeys() {
		// TODO Auto-generated method stub
		return fields;
	}

}
