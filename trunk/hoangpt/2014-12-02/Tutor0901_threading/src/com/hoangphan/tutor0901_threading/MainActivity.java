package com.hoangphan.tutor0901_threading;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageView image;
	private ProgressBar pgrImage;
	public TextView txtCount;
	public TextView txtSing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initUI();
		count(); //count
		sing();
	}
	
	private void sing() {
		//Thread singer = new Thread(new SingRunner());
		//singer.start();
		
		new SingRunner();
	}

	private void count() {
		//tao 1 thread moi
		CountThread counter = new CountThread(this);
		counter.start();
	}

	private void initUI() {
		image = (ImageView) findViewById(R.id.image);
		pgrImage = (ProgressBar) findViewById(R.id.pgrImage);
		txtCount = (TextView) findViewById(R.id.txtCount);
		txtSing = (TextView) findViewById(R.id.txtSing);
	}

	public void loadImage(View v){
		//gia vo chay load anh rat to 5s
		//cho progress bar chay 5
		//cho vao Thread
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					pgrImage.incrementProgressBy(20);
				}
				
				//kiem tra xem app co response hay khong hay bi treo
				//reset va hien thi anh
				pgrImage.setProgress(0);
				
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						image.setImageDrawable(getResources().getDrawable(R.drawable.splash));
					}
				});
			}
		}).start();
	}
	
	public void checkResponse(View v){
		Toast.makeText(this, "I am alive", Toast.LENGTH_SHORT).show();
	}
	
}
