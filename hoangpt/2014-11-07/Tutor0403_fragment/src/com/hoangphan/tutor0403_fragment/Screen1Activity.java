package com.hoangphan.tutor0403_fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Screen1Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen1);
	}
	
	public void showImage(View v){
		EditText edt = (EditText) this.findViewById(R.id.edtImage);
		
		String name = edt.getText().toString();
		Bitmap b = BitmapFactory.decodeFile("/sdcard/Download/dog/"+name+".gif");
		
		ImageView img = (ImageView) this.findViewById(R.id.imgDog);
		img.setImageBitmap(b);
	}	
}
