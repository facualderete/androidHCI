package services;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataProviders.Category;
import DataProviders.subCategory;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import classes.BookItem;
import classes.Item;
import classes.MovieItem;
import classes.User;

public class ApiService extends IntentService {
	
	public static final String CATALOG  = "http://eiffel.itba.edu.ar/hci/service/Catalog.groovy";
	public static final String ORDER    =  "http://eiffel.itba.edu.ar/hci/service/Order.groovy";
	public static final String COMMON   = "http://eiffel.itba.edu.ar/hci/service/Common.groovy";
	public static final String SECURITY = "http://eiffel.itba.edu.ar/hci/service/Security.groovy";

	public static final String GET_CATEGORIES_CMD = "GetCategories";
	public static final String GET_SUBCATEGORIES_CMD = "GetSubCategories";
	public static final String GET_CAT_PRODUCTS_CMD = "GetProductsByCategories";
	public static final String GET_SUBCAT_PRODUCTS_CMD = "GetProductsBySubCategories";
	public static final String GET_PRODUCT_CMD = "GetProductByID";
	public static final String GET_PRODUCTS_CMD = "GetProducts";
	public static final String GET_ORDERS_CMD = "GetOrders";
	public static final String SIGN_IN_CMD = "UserSignIn";

	public static final int API_BAD_RESPONSE_ERROR = -1;
	public static final int STATUS_ERROR = -2;
	public static final int STATUS_ILLEGAL_ARGUMENT = -3;
	public static final int STATUS_OK = 0;
	private static final String TAG = "ToBuy ApiService" ;

	public ApiService() {
		super("API SERVICE");

	}

	@Override
	protected void onHandleIntent(final Intent intent) {
		
		Log.d(TAG, "Llamada a la Api");
		final ResultReceiver receiver = intent.getParcelableExtra("receiver");
		final String command = intent.getStringExtra("command");
		final Integer category_id = intent.getIntExtra("catid", -1);
		final Integer subcategory_id = intent.getIntExtra("subcatid", -1);
		final Integer product_id = intent.getIntExtra("product_id", -1);
		final String criteria = intent.getStringExtra("criteria");
		final String username = intent.getStringExtra("username");
		final String password = intent.getStringExtra("password");
		final Bundle b = new Bundle();

		try {

			if (command.equals(GET_CATEGORIES_CMD)) {
				getCategories(receiver, b);
			} else if (command.equals(GET_SUBCATEGORIES_CMD)) {
				getSubCategories(receiver, b, category_id);
			} else if (command.equals(GET_CAT_PRODUCTS_CMD)) {
				getProductsByCategory(receiver, b, category_id);
			} else if (command.equals(GET_SUBCAT_PRODUCTS_CMD)) {
				getProductsBySubCategory(receiver, b, category_id,
						subcategory_id);
			} else if (command.equals(GET_PRODUCT_CMD)) {
				getProductByID(receiver, b, product_id, category_id);
			} else if (command.equals(GET_PRODUCTS_CMD)) {
				getProducts(receiver, b, criteria);
			} else if (command.equals(SIGN_IN_CMD)) {
				makeLogin(receiver, b, username, password);
			} else if (command.equals(GET_ORDERS_CMD)) {
				getOrders(receiver, b);
			}
		} catch (APIBadResponseException e) {
			b.putSerializable("return", (Serializable) e.err);
			receiver.send(API_BAD_RESPONSE_ERROR, b);
			e.printStackTrace();
		} catch (XMLParseException e) {
			b.putSerializable("return", (Serializable) e.toString());
			receiver.send(STATUS_ILLEGAL_ARGUMENT, b);
			e.printStackTrace();
		} catch (HTTPException e) {
			b.putSerializable("return", (Serializable) e.toString());
			receiver.send(STATUS_ERROR, b);
			e.printStackTrace();
		}

		// Es importante terminar el servicio lo antes posible.
		this.stopSelf();
	}

	private void getOrders(ResultReceiver receiver, Bundle b) throws APIBadResponseException, XMLParseException, HTTPException {
	//	User u = ((KwikApp)getApplication()).getCurrentUser();
	//	List<Order> orders = u.getOrderList();

	//	b.putSerializable("return", (Serializable) orders);
		receiver.send(STATUS_OK, b);
	}

	private void makeLogin(ResultReceiver receiver, Bundle b, String username, String password)
			throws APIBadResponseException, XMLParseException, HTTPException {

		User u = null;

		//u = User.signIn(username, password);

		b.putSerializable("return", (Serializable) u);
		receiver.send(STATUS_OK, b);
	}

	private void getProducts(ResultReceiver receiver, Bundle b, String criteria) throws APIBadResponseException,
			XMLParseException, HTTPException {

	List<Item> p = null;

		//p = new Product().getProducts("DESC", criteria);

		b.putSerializable("return", (Serializable) p);
		receiver.send(STATUS_OK, b);
	}

	private void getProductByID(ResultReceiver receiver, Bundle b, int product_id, int category) throws APIBadResponseException,
			XMLParseException, HTTPException {

		Item p = null;
		if (category == 1)
		{
				    p = new MovieItem(1,23,"Producto de prueba",  1, 1, 50,null);
		}
		else
			if (category ==2)
			{
				p = new BookItem(1,23,"Producto de prueba",  1, 1, 50,null);

				
			}
	  

	//	p = Product.getProduct(product_id);

		b.putSerializable("return", (Serializable) p);
		receiver.send(STATUS_OK, b);
	}

	private void getProductsByCategory(ResultReceiver receiver, Bundle b, int category_id)
			throws APIBadResponseException, XMLParseException, HTTPException {

		List<Item> products = null;

	//	Category c = new Category();
	//	c.id = category_id;

	//	products = c.getProducts();

		b.putSerializable("return", (Serializable) products);
		receiver.send(STATUS_OK, b);
	}

	private void getProductsBySubCategory(ResultReceiver receiver, Bundle b, int catid, int subcatid )
			throws APIBadResponseException, XMLParseException, HTTPException {

		List<Item> items = new ArrayList<Item>();

		if (catid == 1 )
		{
			 MovieItem m = null;
			 MovieItem ma= null;
			 MovieItem ms= null;
				   if (subcatid == 1)	
				   {
					    m = new MovieItem(1, 123, "Pelicula 1", 1, 1, 50, null);
					    ma = new MovieItem(2, 143, "Pelicula 2", 1, 1, 80, null);
					    ms = new MovieItem(3, 113, "Pelicula 3", 1, 1, 20, null);
					    items.add(m);	
						   items.add(ma);	
						   items.add(ms);
		
				   }
				   else
					   if(subcatid == 2)
					   {
						    m = new MovieItem(4, 123, "Pelicula 4", 1, 2, 24, null);
						    ma = new MovieItem(5, 117, "Pelicula 5", 1, 2, 47, null);
						    ms= new MovieItem(6, 119, "Pelicula 6", 1, 2, 210, null);
						    items.add(m);	
							   items.add(ma);	
							   items.add(ms);
					   }
		   
		 
		   
		}
		else
		if (catid == 2)
		{	BookItem ba = null;
			BookItem bb = null;
			BookItem bc = null;

		
			if (subcatid == 1)
			{
				 ba = new BookItem(7, 24, "Libro 1", 2, 1, 67, null);
				 bb = new BookItem(8, 122, "Libro 2", 2, 1, 33, null);
				 bc = new BookItem(9, 52, "Libro 3", 2, 1, 83, null);
				 items.add(ba);	
				   items.add(bb);	
				   items.add(bc);
			}
			else
				if (subcatid ==2)
				{
					 ba = new BookItem(10, 32, "Libro 4", 2, 2, 12, null);
					 bb = new BookItem(11, 82, "Libro 5", 2, 2, 56, null);
					 bc = new BookItem(12, 75, "Libro 6", 2, 2, 345, null);
					 items.add(ba);	
					   items.add(bb);	
					   items.add(bc);
				}
				
		}
	

		b.putSerializable("return", (Serializable) items);
		receiver.send(STATUS_OK, b);
	}

	private void getSubCategories(ResultReceiver receiver, Bundle b, int category_id) throws APIBadResponseException,
			XMLParseException, HTTPException {

		Log.d(TAG, "Obteniendo SubCategorias");
		List<subCategory> subCategories = new ArrayList<subCategory>();
		if (category_id == 1)
		{	subCategory cat1 = new subCategory(1, "1", "SubCategoria de DVD 1", category_id);
			subCategory cat2 = new subCategory(1, "2", "SubCategoria de DVD 2", category_id);
			subCategories.add(cat1);subCategories.add(cat2);

		}else
		if (category_id == 2)
		{
			subCategory cat1 = new subCategory(2, "1", "SubCategoria de Books 1", category_id);
			subCategory cat2 = new subCategory(2, "2", "SubCategoria de Books 2", category_id);
			subCategories.add(cat1);subCategories.add(cat2);

		}
	
		b.putSerializable("return", (Serializable) subCategories);
		receiver.send(STATUS_OK, b);
	}

	private void getCategories(ResultReceiver receiver, Bundle b) throws APIBadResponseException, XMLParseException,
			HTTPException {
		Log.d(TAG, "Obteniendo Categorias");
		List<Category> categories = new ArrayList<Category>();

//		categories.add(new Category(1, "1", "DVD"));
//		categories.add(new Category(2, "2", "Books"));
//		categories.add(new Category(3, "3", "Hidden"));
		//	categories = Category.getCategoryList();

		Map<String, String> URL = new HashMap<String,String>();
		URL.put("method", "GetCategoryList");
		URL.put("language_id","1");                                                                                 //TODO VER IDIOMA

		APIConnection a = null;
		try {
			a = APIConnection.get(CATALOG, URL, GET_CATEGORIES_CMD);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		b.putSerializable("return", (Serializable) a.categories);
		receiver.send(STATUS_OK, b);
		
		
		
		// Optional: Some caching
		//return r.categories;
		
		
		
		
	}
	
}
