package DataProviders;

import java.util.List;
import java.util.Map;

public interface CategoriesProvider {

	public List<Category> getCategories();

	public List<? extends Map<String, ?>> getCategoryAsMap();

	public String[] getMapKeys();
	
	
}
