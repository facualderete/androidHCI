package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataProviders.subCategoriesProvider;
import DataProviders.subCategory;

public class AbstractsubCategoryProvbider implements subCategoriesProvider {

public static final String[] fields = { "id", "code", "name", "category" };

	public List<subCategory> getsubCategories() {
		
		List<subCategory> l = new ArrayList<subCategory>();
		l.add(new subCategory(0, null, null, 0));
		return l;
	}

	public List<? extends Map<String, ?>> getsubCategoryAsMap() {
		List<subCategory> subcategories = getsubCategories();
		List<Map<String, String>> transformedsubCategories = new ArrayList<Map<String, String>>();
		for (subCategory t : subcategories) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(fields[0], t.getCateg().toString());
			map.put(fields[1], t.getCode());
			map.put(fields[2], t.getId().toString());
			map.put(fields[3], t.getName());
			transformedsubCategories.add(map);
		}
		return transformedsubCategories;
	}

	public String[] getMapKeys() {
		// TODO Auto-generated method stub
		return fields;
	}
	
}
