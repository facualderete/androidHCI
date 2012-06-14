package activites;

import java.util.List;

import services.ApiService;
import classes.Item;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import ar.hci.R;

public class loadProductActivity extends toBuyActivity implements OnClickListener, OnLongClickListener{

	protected static final String TAG = "loadProductActivity";
	public Double price;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);
		Bundle subLevelInformation = this.getIntent().getExtras();
		
		final Integer itemid = subLevelInformation.getInt("product_id",-1);
		final String itemname = subLevelInformation.getString("itemname");
		price = subLevelInformation.getDouble("price",-1);
		final Integer subcategory = subLevelInformation.getInt("subcatid",-1);
		final Integer category = subLevelInformation.getInt("catid",-1);
	//	final Integer code = subLevelInformation.getInt("code");
		
		boolean isSearchIntent = itemname == null;

		if (!isSearchIntent)
			this.setTitle("-"+itemname+"-" + "Id " + itemid + "Category " + category  );
		else
			this.setTitle("Buscando .. ");
		
		Intent intent = new Intent(Intent.ACTION_SYNC, null, this,ApiService.class);
		intent.putExtra("command", ApiService.GET_PRODUCT_CMD);
		intent.putExtra("product_id", itemid);
		intent.putExtra("itemname", itemname);
		intent.putExtra("catid", category );
		intent.putExtra("subcategory", subcategory );
		
		intent.putExtra("receiver", new ResultReceiver(new Handler()) {
			@Override
			protected void onReceiveResult(int resultCode, Bundle resultData) {
				super.onReceiveResult(resultCode, resultData);

				if (resultCode == ApiService.STATUS_OK) {

					Log.d(TAG, "ToBuy ApiService Status OK");

					@SuppressWarnings("unchecked")
					Item itemToShow = (Item) resultData.getSerializable("return");
					showItem(itemToShow);

				} else if (resultCode == ApiService.STATUS_ERROR) {
					Log.d(TAG, "ToBuy ApiService Connection error.");
				} else {
					Log.d(TAG, "ToBuy ApiService Unknown error.");
				}

			}

		

		});
		

		Button vi = (Button) findViewById(R.id.descriptionbutton);

		vi.setOnClickListener(this);
		vi.setOnLongClickListener(this);

		startService(intent);

		
	}
	protected void showItem(Item itemToShow) {
		TextView vi = (TextView) findViewById(R.id.name);
		vi.setText( itemToShow.getName());
		
		TextView va = (TextView) findViewById(R.id.price);
		Double price = itemToShow.getPrice();
		String pricen = price.toString();
		va.setText(pricen);
		
		Integer sales = itemToShow.getSales_rank();
		String saless = sales.toString();
		TextView vb = (TextView) findViewById(R.id.sales);
		vb.setText(saless);
		
		TextView vc = (TextView) findViewById(R.id.category);
		Integer cat = itemToShow.getCateg_id();
		String cats = cat.toString();
		vc.setText( cats);
		
		TextView vd = (TextView) findViewById(R.id.subcategory);
		Integer scat = itemToShow.getScateg_id();
		String scats = scat.toString();
		vd.setText( scats);



	}
	@Override
	public boolean onLongClick(View arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClick(View v) {
		Button b = (Button) findViewById(R.id.descriptionbutton);
		View vi = (View) findViewById(R.id.description);
		if (vi.getVisibility() == View.GONE)
			{
		
			b.setText("Ocultar Descripcion");
				vi.setVisibility(View.VISIBLE);
			}
		else
			{
				b.setText("Ver Descripcion");
				vi.setVisibility(View.GONE);
			}
	}
	

}
