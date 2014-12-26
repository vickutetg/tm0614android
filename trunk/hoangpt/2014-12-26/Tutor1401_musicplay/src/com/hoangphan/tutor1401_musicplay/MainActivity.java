package com.hoangphan.tutor1401_musicplay;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void play(View v){
		//thu muc chua file nhac
		String path = "file:///sdcard/Download/Giuem.mp3";

		//goi app choi nhac
		//gọi music player ra chơi file nhạc đó
		//Intent i = new Intent(Intent.ACTION_VIEW); //android.intent.action.VIEW
		//i.setDataAndType(Uri.parse(path), "audio/mp3"); //linkify
		//startActivity(i);
		
		//video
		
		//pdf
		
		//xls
		
		//...
		
		//map
		
		
		//send broadcast
		Intent i1 = new Intent("tour_di_bien");
		i1.putExtra("price", 900);
		sendBroadcast(i1);
	}
	
	public void map(View v){
		String adr = ((EditText) findViewById(R.id.edtAddress)).getText().toString();
		String linkify = "geo://0,0?q="+adr;
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkify)));
	}
}
