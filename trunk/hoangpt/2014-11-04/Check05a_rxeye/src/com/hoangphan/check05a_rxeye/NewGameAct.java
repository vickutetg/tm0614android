package com.hoangphan.check05a_rxeye;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;

public class NewGameAct extends Activity {
	
	int number = 0;
	Button b1, b2;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);
		
		//create 1 button
		final GridLayout layout = (GridLayout) findViewById(R.id.layout);
		
		//reponsive
		WindowManager windowManager = getWindowManager();
		Display windowDisplay = windowManager.getDefaultDisplay();
		int w = windowDisplay.getWidth();
		int h = windowDisplay.getHeight();
		
		int buttonWidth = w/3-15;
		int col = (int) Math.floor(h/(buttonWidth+15));
		number = 3*col;
		
		//loop, add into layout
		for (int i = 0; i < 3*col; i++) {
			Button b = new Button(this);
			b.setHeight(buttonWidth);
			b.setWidth(buttonWidth);
			b.setId(10+i);
			b.setBackgroundColor(Color.BLACK);
			layout.addView(b);
		}
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			Random r = new Random();
			
			@Override
			public void run() {
				
				
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						if(b1 != null){
							b1.setBackgroundColor(Color.BLACK);
							b2.setBackgroundColor(Color.BLACK);
						}
						
						//C1
						int i = r.nextInt(9);
						b1 = (Button) findViewById(10+i);
						b1.setBackgroundColor(Color.RED);
						
						//check j, while j1=i
						int j = 0;
						do {
							j = r.nextInt(9);
						} while (j==i);
						b2 = (Button) findViewById(10+j);
						b2.setBackgroundColor(Color.RED);						
					}
				});
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000); //async task
	}
}
