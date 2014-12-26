package com.hoangphan.tutor1401_notif;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void sendNotif(View v){
		//build 1 notification 
		String notifMes = ((EditText)findViewById(R.id.txtNotif)).getText().toString();
		
		//new Notification
		Notification.Builder builder = new Notification.Builder(this)
		    .setSmallIcon(android.R.drawable.ic_delete)
		    .setContentText(notifMes);
		
		// Sets an ID for the notification
		int notifId = 100000; //hexa
		
		// Gets an instance of the NotificationManager service
		NotificationManager notifManager = 
		        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);		
		
		//gửi sang notification service 
		//Notification notif = new Notification();
		Notification notif = builder.build();
		long[] times = new long[]{5l, 50l, 60l, 5l};
		notif.vibrate = times;
		notif.sound = Uri.parse("file:///sdcard/Download/GiuEm.mp3");
		
		//tạo ra 1 intend (pending intent) 
		Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
		resultIntent.putExtra("notifId", notifId);
		
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(ResultActivity.class);
		stackBuilder.addNextIntent(resultIntent);		
		
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		        		0, 
		        		PendingIntent.FLAG_UPDATE_CURRENT);
		
		/*PendingIntent resultPendingIntent =
			    PendingIntent.getActivity(
			    this,
			    0,
			    resultIntent,
			    PendingIntent.FLAG_UPDATE_CURRENT
		);*/
		
		//goi broadcast/receiver
		//notif.setLatestEventInfo(this, "hehe", "hihi", resultPendingIntent);
		
		builder.setContentIntent(resultPendingIntent);
		notifManager.notify(notifId, notif);
	}
}
