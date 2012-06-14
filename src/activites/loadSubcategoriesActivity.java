package activites;

import java.util.HashMap;
import java.util.List;

import services.ApiService;
import services.GetAsMap;
import DataProviders.subCategory;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import ar.hci.R;

public class loadSubcategoriesActivity extends toBuyActivity implements
		OnItemClickListener, OnItemLongClickListener {

	protected static final String TAG = "SubCategoryActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subcat_list);
		Bundle subLevelInformation = this.getIntent().getExtras();
		final Integer catid = subLevelInformation.getInt("catid", -1);
		final Integer subcategory_id = subLevelInformation.getInt("subcatid",
				-1);
		final String catname = subLevelInformation.getString("catname");

		boolean isSearchIntent = catname == null;

		if (!isSearchIntent)
			this.setTitle("Categ " + catname + "Id " + subcategory_id + "IdCat " + catid );
		else
			this.setTitle("Buscando .. ");

		Intent intent = new Intent(Intent.ACTION_SYNC, null, this,
				ApiService.class);
		intent.putExtra("command", ApiService.GET_SUBCATEGORIES_CMD);
		intent.putExtra("catid", catid);

		intent.putExtra("receiver", new ResultReceiver(new Handler()) {
			@Override
			protected void onReceiveResult(int resultCode, Bundle resultData) {
				super.onReceiveResult(resultCode, resultData);

				if (resultCode == ApiService.STATUS_OK) {

					Log.d(TAG, "ToBuy ApiService Status OK");

					@SuppressWarnings("unchecked")
					List<subCategory> list = (List<subCategory>) resultData
							.getSerializable("return");
					populatesubcategoryList(list);

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

	private void populatesubcategoryList(List<subCategory> list) {

		String[] map_fields = { "name", "id" , "code" , "category" };
		String[] getMapKeys = { "name" };

		ListAdapter a = new SimpleAdapter(this, GetAsMap.getAsMap(list,
				map_fields), R.layout.subcat_item, getMapKeys,
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

		Integer catid = (Integer) xy.get("category");
		String  subcatname = (String) xy.get("name");
		Integer subcatid = (Integer) xy.get("id");
//		Log.d("subcatid value = " , subcatid.toString());
	//	Integer code = (Integer) xy.get("code");
		
		Intent i = new Intent(arg1.getContext(), loadProductsbySubcategoriesActivity.class);
		i.putExtra("subcatid", subcatid);
		i.putExtra("catid", catid);
		i.putExtra("catname", subcatname);
	//	i.putExtra("code", code);

		startActivityForResult(i, 0);

	}
}
