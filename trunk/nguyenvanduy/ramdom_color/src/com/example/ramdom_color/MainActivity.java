package com.example.ramdom_color;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{
	
	int array[] = {Color.RED, Color.BLUE, Color.GREEN};
	Button btn_random;
	TextView txt_color;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_random = (Button)findViewById(R.id.btn_random);
		txt_color = (TextView)findViewById(R.id.txt_color);
	
		btn_random.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Random random = new Random();
				
				int _random = random.nextInt(3);
				Log.d("Random = ", " "+_random);
				txt_color.setBackgroundColor(array[_random]);
				
			}
		});
		
	}

	
}
