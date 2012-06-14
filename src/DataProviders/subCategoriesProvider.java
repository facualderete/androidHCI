package DataProviders;

import java.util.List;
import java.util.Map;

public interface subCategoriesProvider {

	public List<subCategory> getsubCategories();

	public List<? extends Map<String, ?>> getsubCategoryAsMap();

	public String[] getMapKeys();
}
