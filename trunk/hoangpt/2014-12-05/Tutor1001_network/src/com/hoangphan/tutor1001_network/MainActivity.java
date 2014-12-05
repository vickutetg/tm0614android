package com.hoangphan.tutor1001_network;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageView image;
	private String imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imageUri = "http://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/Snake_River_%285mb%29.jpg/1280px-Snake_River_%285mb%29.jpg";
		initUI(); //get ui widget in xml
	}
	
	private void initUI() {
		image = (ImageView) findViewById(R.id.image);
		
	}

	/**
	 * event onclick into button
	 * @param v
	 */
	public void loadImg(View v){
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		
		Bitmap bm = downloadImge(imageUri);
		image.setImageBitmap(bm); //hien thi len UI
	}
	
	private Bitmap downloadImge(String imageUri) {
		//mo connection toi anh
		URL url = null;
		Bitmap bm = null;
		
		//hien thi progress dialog
		
		try {
			url = new URL(imageUri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			//goi mot request ra (GET, POST, PUT, DELETE)
			InputStream is = connection.getInputStream();
			
			//lay cai binary ve local (input stream)
			bm = BitmapFactory.decodeStream(is);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bm;
	}

	public void checkLive(View v){
		Toast.makeText(this, "Iam still alive", 0).show(); //short
	}
	
	private class LoadVeryBigImage extends AsyncTask<String, Void, Long>{
		Bitmap bm; 
		String imageUri;
		
		public LoadVeryBigImage(String imageUri) {
			this.imageUri = imageUri;
		}

		@Override
		protected Long doInBackground(String... params) {
			bm = downloadImge(imageUri);
			
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					image.setImageBitmap(bm); //hien thi len UI
				}
			});
			
			return null;
		}

		@Override
		protected void onPostExecute(Long result) {
			super.onPostExecute(result);
		}
	}
	
	public void loadImgAsync(View v){
		String kekek = "kekek";
		new LoadVeryBigImage(imageUri).execute(kekek);
	}
}
