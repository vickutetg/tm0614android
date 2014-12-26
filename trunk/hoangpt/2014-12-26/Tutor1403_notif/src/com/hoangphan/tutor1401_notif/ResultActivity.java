package com.hoangphan.tutor1401_notif;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		//xoa notif 
		// Gets an instance of the NotificationManager service
		NotificationManager notifManager = 
		        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);		
		
		//gửi sang notification service 
		int notifId = 100000;
		notifManager.cancel(notifId);
	}
}
