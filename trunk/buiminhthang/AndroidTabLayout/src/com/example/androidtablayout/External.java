package com.example.androidtablayout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class External extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.external_layout);
		Button btCallbrowser, btCallgallery, btCallplayer;
		btCallbrowser = (Button) findViewById(R.id.btCallbrowser);
		btCallgallery = (Button) findViewById(R.id.btCallgallery);
		btCallplayer = (Button) findViewById(R.id.btCallplayer);

		btCallbrowser.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://www.google.com"));
				startActivity(browserIntent);
			}
		});
	}
}
