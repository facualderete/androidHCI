package activites;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import ar.hci.R;




public class startActivity extends Activity {
	private ImageButton categoriesButton;
	public static final int MILLIS_TIME_TO_WAIT = 5000;
	public static final int STOP = 0;

	@SuppressWarnings("unused")
	private Handler splashHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case startActivity.STOP:
					   Intent intent = new Intent(startActivity.this, loadCategoriesActivity.class);
						startActivity(intent);
			
					break;
			}
			super.handleMessage(msg);
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
	/*	Intent intent = new Intent(SplashActivity.this, SearchActivity.class);
		startActivity(intent);
		SplashActivity.this.finish();*/
     //   Button button = (Button)findViewById(R.id.catlistbutton);
		Message message = new Message();
		message.what = startActivity.STOP;
    //    splashHandler.sendMessageDelayed(message, MILLIS_TIME_TO_WAIT);
     
     //   button.setOnClickListener(this);
		categoriesButton = (ImageButton) findViewById(R.id.catlistbutton);
	    categoriesButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				
				Intent intent = new Intent(startActivity.this,	loadCategoriesActivity.class);
				startActivity(intent);
			}
		});
	    
        
    }

    
//    public void onClick(View v) {
//        switch(v.getId())
//        {
//            case(R.id.catlistbutton): Intent intent = new Intent(startActivity.this, loadCategoriesActivity.class);
//    		startActivity(intent);
//    	
//    		break;
//            
//        }
//      }
}