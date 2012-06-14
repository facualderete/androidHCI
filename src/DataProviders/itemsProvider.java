package DataProviders;

import java.util.List;
import java.util.Map;

import classes.Item;


public interface itemsProvider {

	
	public List<Item> getItems();

	public List<? extends Map<String, ?>> getItemsAsMap();

	public String[] getMapKeys();
	
}
