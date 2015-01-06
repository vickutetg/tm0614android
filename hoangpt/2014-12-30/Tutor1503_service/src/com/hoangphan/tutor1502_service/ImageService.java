package com.hoangphan.tutor1502_service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ImageService extends Service {

	private Timer timer;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(getBaseContext(), "Start service", Toast.LENGTH_SHORT).show();
		
		//chay vo han//async task
		/*TimerTask timeTask = new TimerTask() {
			int count = 0;
			@Override
			public void run() {
				Log.d("count", "Be hoc dem tu 1-100: "+count);
				count++;
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timeTask, 0, 1000);*/
		
		//download anh
		String url = "http://vnexpress.com/abc.jpg";
		int byteDl = downloadImg(url);
		Toast.makeText(getBaseContext(), "Download "+ byteDl+ " byte", Toast.LENGTH_SHORT).show();
		
		return START_STICKY;
	}

	private int downloadImg(String url) {
		try {
			URL urlImg = new URL(url);
			Thread.sleep(5000);
		} catch (MalformedURLException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return 1000;
	}

	@Override
	public void onDestroy() {
		Toast.makeText(getBaseContext(), "Stop service", Toast.LENGTH_SHORT).show();
		stopSelf();
		//timer.cancel();
	}
}
