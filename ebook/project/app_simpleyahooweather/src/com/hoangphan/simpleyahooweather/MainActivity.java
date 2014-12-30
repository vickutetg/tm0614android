package com.hoangphan.simpleyahooweather;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity {
	
	private Cursor mCursor;
	private DbConnection dbConn;
	private UIDrawer uiDrawer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView (R.layout.activity_main);
		
		dbConn = new DbConnection(this, mCursor);
		uiDrawer = new UIDrawer(this, dbConn);
		uiDrawer.drawCityList();
	

	}
	
	@Override
	protected void onResume () {
		super.onResume();
	}
	
	public void onDestroy() {
	   super.onDestroy();
	   if (mCursor != null) {
		      mCursor.close();
	   }
	}
	
	@Override
	public void onBackPressed() {
	   if (findViewById(R.id.addCityWrapper).getVisibility() == View.VISIBLE) {
		   findViewById(R.id.addCityWrapper).setVisibility(View.INVISIBLE);
	   } else if (findViewById(R.id.informationWrapper).getVisibility() == View.VISIBLE) {
		   findViewById(R.id.informationWrapper).setVisibility(View.INVISIBLE);
	   } else {
		   super.onBackPressed();
	   }
	}

}
