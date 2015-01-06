package com.hoangphan.tutor1502_service;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ImgIntentService extends IntentService {

	public ImgIntentService() {
		super("ImgIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String[] urls = new String[]{
			"http://vnexpress.com/1.jpg",	
			"http://vnexpress.com/2.jpg",	
			"http://vnexpress.com/3.jpg",	
			"http://vnexpress.com/4.jpg",	
			"http://vnexpress.com/5.jpg",	
			"http://vnexpress.com/6.jpg",	
			"http://vnexpress.com/7.jpg",	
			"http://vnexpress.com/8.jpg",	
			"http://vnexpress.com/9.jpg",	
			"http://vnexpress.com/10.jpg",	
		};
		
		int dlAccum = 0;
		for (String url : urls) {
			int dlByte = downloadImg(url);
			dlAccum += dlByte;
			Log.d("download", "Download " +dlAccum+ " bytes");
			//Toast.makeText(getApplicationContext(), "Download " +dlAccum+ " bytes", Toast.LENGTH_SHORT).show();
		}
	}
	
	private int downloadImg(String url) {
		try {
			URL urlImg = new URL(url);
			Thread.sleep(3000);
		} catch (MalformedURLException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return 1000;
	}
}
