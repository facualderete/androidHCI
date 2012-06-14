package activites;

import java.util.HashMap;
import java.util.List;

import services.ApiService;
import services.GetAsMap;
import DataProviders.CategoriesProvider;
import DataProviders.Category;
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

public class loadCategoriesActivity extends toBuyActivity implements
		OnItemClickListener, OnItemLongClickListener {

	private String TAG = "loadCategoriesActivity";
	@SuppressWarnings("unused")
	private CategoriesProvider categoryProvider;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.cat_list);

		Intent intent = new Intent(Intent.ACTION_SYNC, null, this,
				ApiService.class);

		intent.putExtra("command", ApiService.GET_CATEGORIES_CMD);
		/*
		 * Se pasa un callback (ResultReceiver), con el cual se procesará la
		 * respuesta del servicio. Si se le pasa null como parámetro del
		 * constructor se usa uno de los threads disponibles del proceso. Dado
		 * que en el procesamiento del mismo se debe modificar la UI, es
		 * necesario que ejecute con el thread de UI. Es por eso que se lo
		 * instancia con un objeto Handler (usando el el thread de UI para
		 * ejecutarlo).
		 */
		intent.putExtra("receiver", new ResultReceiver(new Handler()) {
			@Override
			protected void onReceiveResult(int resultCode, Bundle resultData) {
				super.onReceiveResult(resultCode, resultData);

				if (resultCode == ApiService.STATUS_OK) {

					Log.d(TAG, "ToBuy ApiService status OK");

					@SuppressWarnings("unchecked")
					List<Category> list = (List<Category>) resultData
							.getSerializable("return");

					populatecategoryList(list);

				} else if (resultCode == ApiService.STATUS_ERROR) {
					Log.d(TAG, "ToBuy Connection error.");
				} else {
					Log.d(TAG, "ToBuy Unknown error.");
				}
			}

		});

		ListView vi = (ListView) findViewById(R.id.listview);

		vi.setOnItemClickListener(this);
		vi.setOnItemLongClickListener(this);

		startService(intent);

		// categoryProvider = new CategoryProviderMock();
		//
		// ListAdapter adapter = new SimpleAdapter(this,
		// categoryProvider.getCategoryAsMap(), R.layout.cat_item,
		// categoryProvider.getMapKeys(), new int[] { R.id.id, R.id.name });
		//
		// setListAdapter(adapter);
		//
		// // this.setContentView(R.layout.cat_item);
		// int size = categoryProvider.getCategoryAsMap().size();
		// for (int i = 0; i < size; i++) {
		//
		// }
		// Button dvds = (Button)
		// findViewById(this.getIntent().getExtras().getInt("id"));
		// dvds.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// Intent intent = new Intent(v.getContext(),
		// loadSubcategoriesActivity.class);
		// intent.putExtra("category_id", dvds.);
		// intent.putExtra("category_name",
		// this.getIntent().getExtras().getInt("name"));
		// startActivityForResult(intent, 0);
		// }
		// });

		/*
		 * Intent intent = new Intent(loadCategoriesActivity.this,
		 * loadSubcategoriesActivity.class); intent.putExtra("DVD",1);
		 * intent.putExtra("BOOKS",2); //startActivity(intent); }
		 */
	}

	protected void populatecategoryList(List<Category> list) {

		String[] map_fields = { "name", "id" , "category" };
		String[] getMapKeys = { "name" };

		ListAdapter adapter = new SimpleAdapter(this, GetAsMap.getAsMap(list,
				map_fields), R.layout.cat_item, getMapKeys,
				new int[] { R.id.title });
		

		ListView vi = (ListView) findViewById(R.id.listview);
		vi.setAdapter(adapter);

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		ListView vi = (ListView) arg0;
		HashMap<String, Object> xy = (HashMap<String, Object>) vi
				.getItemAtPosition(arg2);

		Integer catid = (Integer) xy.get("id");
		String catname = (String) xy.get("name");
		Integer subcatid = (Integer) xy.get("category");
		boolean lookingForCategories = (subcatid == null);
		Intent i;
		if (!lookingForCategories) {
			i = new Intent(arg1.getContext(), this.getClass());
			i.putExtra("subcatid", subcatid);
		} else {
			i = new Intent(arg1.getContext(), loadSubcategoriesActivity.class);

		}
		i.putExtra("catid", catid);
		i.putExtra("catname", catname);
		startActivityForResult(i, 0);
	}
}