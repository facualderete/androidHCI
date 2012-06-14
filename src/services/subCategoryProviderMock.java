package services;

import java.util.ArrayList;
import java.util.List;

import DataProviders.subCategory;


	public class subCategoryProviderMock extends AbstractsubCategoryProvbider {

		@Override
		public List<subCategory> getsubCategories() {
			List<subCategory> subcats = new ArrayList<subCategory>();

			subcats.add(new subCategory(1,"1", "Action", 1));
			subcats.add(new subCategory(2,"2", "Drama", 1));
			subcats.add(new subCategory(3,"1", "Libro subcat", 2));
			return subcats;

		}
	}
