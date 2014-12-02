package com.hoangphan.tutor0902_asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageView image;
	private ProgressBar pgrImage;
	private GridLayout gridText;
	public String[] params = new String[]{
			"Hanoi", "TpHCM", "Hue",
			"Hanoi1", "TpHCM1", "Hue1",
			"Hanoi2", "TpHCM2", "Hue2", "Danang"
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initUI();
	}
	
	private void initUI() {
		image = (ImageView) findViewById(R.id.image);
		pgrImage = (ProgressBar) findViewById(R.id.pgrImage);
		gridText = (GridLayout) findViewById(R.id.gridText);
		
		for (int i = 0; i < params.length; i++) {
			TextView child = new TextView(this);
			child.setText("init...");
			gridText.addView(child);
		}
	}
	
	private void displayImg(){
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//pgrImage.incrementProgressBy(20);
		}
		
		//kiem tra xem app co response hay khong hay bi treo
		//reset va hien thi anh
		//pgrImage.setProgress(0);
		
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				image.setImageDrawable(getResources().getDrawable(R.drawable.splash));
			}
		});		
	}
	
	//non responsive
	public void loadImage(View v){
		displayImg();
	}
	
	public void ansynloadImage(View v){
		//String name = "hoang";
		//new LoadVeryHeavyBigImage().execute(name);
		new LoadVeryHeavyText().execute(params);
		
	}
	
	public void checkResponse(View v){
		Toast.makeText(this, "I am alive", Toast.LENGTH_SHORT).show();
	}
	
	private class LoadVeryHeavyBigImage extends AsyncTask<String, Integer, Void>{

		@Override
		protected Void doInBackground(String... params) {
			displayImg();
			return null;
		}
	}
	
	private class LoadVeryHeavyText extends AsyncTask<String, Long, String>{

		int count = 0;
		
		@Override
		protected String doInBackground(String... stringArr) {
			//int lengh = params.length;
			for (int i = 0; i < params.length; i++) {
				count = i;
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						TextView v = (TextView) gridText.getChildAt(count);
						v.setText(params[count]);
					}
				});
				
				try { //gia lap viec load text
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				publishProgress((long) i+1); //postMessage
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String result) {
			pgrImage.setProgress(0);
			Toast.makeText(MainActivity.this, "Loading finish", Toast.LENGTH_SHORT).show();
		}

		@Override
		protected void onProgressUpdate(Long... values) {
			long nrText = values[0];
			Log.d("count", nrText+"");
			int percent = (int) nrText * 100 /params.length;
			pgrImage.setProgress(percent);
			
		}

		@Override
		protected void onCancelled(String result) {
			// TODO Auto-generated method stub
			super.onCancelled(result);
		}
	}
}
