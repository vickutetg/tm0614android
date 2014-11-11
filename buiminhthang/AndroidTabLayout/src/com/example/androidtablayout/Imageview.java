package com.example.androidtablayout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class Imageview extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);
		ImageView imageview = (ImageView) findViewById(R.id.imageview);
		imageview.setImageResource(R.drawable.android);
	}
}
