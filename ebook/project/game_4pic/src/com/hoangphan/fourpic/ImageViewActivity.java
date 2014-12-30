package com.hoangphan.fourpic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageViewActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//button
		ImageView iconButton = (ImageView)findViewById(R.id.imageView1);
		iconButton.setOnClickListener(this);
	}
	
	//implements OnClickListener
	public void onClick(View v) {
		Toast.makeText(this, R.string.hintImageDesc, Toast.LENGTH_LONG).show();
	}
}