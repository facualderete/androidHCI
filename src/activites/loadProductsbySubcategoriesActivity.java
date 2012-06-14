package activites;

import java.util.HashMap;
import java.util.List;

import services.ApiService;
import services.GetAsMap;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import ar.hci.R;
import classes.Item;

public class loadProductsbySubcategoriesActivity extends toBuyActivity implements
OnItemClickListener, OnItemLongClickListener{

	protected static final String TAG = "ProductsbySubCategoryActivity";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prodcat_list);
		Bundle subLevelInformation = this.getIntent().getExtras();
		final Integer catid = subLevelInformation.getInt("catid", -1);
		final Integer subcatid = subLevelInformation.getInt("subcatid",-1);
	//	final Integer code = subLevelInformation.getInt("code");
		final String catname = subLevelInformation.getString("catname");
		
		boolean isSearchIntent = catname == null;

		if (!isSearchIntent)
			this.setTitle("-"+catname+"-" + "Id " + subcatid + "IdCat " + catid  );
		else
			this.setTitle("Buscando .. ");
		
		Intent intent = new Intent(Intent.ACTION_SYNC, null, this,
				ApiService.class);
		intent.putExtra("command", ApiService.GET_SUBCAT_PRODUCTS_CMD);
		intent.putExtra("catid", catid);
		intent.putExtra("subcatid", subcatid);
		intent.putExtra("catname", catname);
		
		intent.putExtra("receiver", new ResultReceiver(new Handler()) {
			@Override
			protected void onReceiveResult(int resultCode, Bundle resultData) {
				super.onReceiveResult(resultCode, resultData);

				if (resultCode == ApiService.STATUS_OK) {

					Log.d(TAG, "ToBuy ApiService Status OK");

					@SuppressWarnings("unchecked")
					List<Item> list = (List<Item>) resultData.getSerializable("return");
					populateProductsList(list);

				} else if (resultCode == ApiService.STATUS_ERROR) {
					Log.d(TAG, "ToBuy ApiService Connection error.");
				} else {
					Log.d(TAG, "ToBuy ApiService Unknown error.");
				}

			}

		

		});
		

		ListView vi = (ListView) findViewById(R.id.listview);

		vi.setOnItemClickListener(this);
		vi.setOnItemLongClickListener(this);

		startService(intent);

		
	}
	
	
	
	
	protected void populateProductsList(List<Item> list) {
		String[] map_fields = { "name", "id" , "image" , "price", "subcategory_id" , "category_id" };
		String[] getMapKeys = { "name" };

		ListAdapter a = new SimpleAdapter(this, GetAsMap.getAsMap(list,
				map_fields), R.layout.prodcat_item, getMapKeys,
				new int[] { R.id.title });

		ListView vi = (ListView) findViewById(R.id.listview);
		vi.setAdapter(a);
		
	}



	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		ListView vi = (ListView) arg0;
		@SuppressWarnings("unchecked")
		HashMap<String, Object> xy = (HashMap<String, Object>) vi.getItemAtPosition(arg2);

		Integer itemid = (Integer) xy.get("id");
		String itemname = (String) xy.get("name");
		Double price = (Double) xy.get("price");
		Integer subcategory = (Integer) xy.get("subcategory_id");
		Integer category = (Integer) xy.get("category_id");

		Intent i;


			i = new Intent(arg1.getContext(), loadProductActivity.class);

			i.putExtra("product_id",itemid);
			i.putExtra("itemname", itemname);
			i.putExtra("price", price );
			i.putExtra("subcatid", subcategory );
			i.putExtra("catid", category );
		startActivityForResult(i, 0);
	}

}
