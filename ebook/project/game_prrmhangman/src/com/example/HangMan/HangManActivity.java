package com.example.HangMan;        
        
import android.app.Activity;        
import android.os.Bundle;        
        
public class HangMan extends Activity        
{        
               Button btnContinue;        Button btnNewGame;        Button btnAbout;        Button btnExit;        @Override        public void onCreate(Bundle savedInstanceState) {        	super.onCreate(savedInstanceState);        	setContentView(R.layout.main);        	        	btnContinue = (Button)findViewById(R.id.continue_button);        	btnNewGame = (Button)findViewById(R.id.new_button);        	btnAbout = (Button)findViewById(R.id.about_button);        	btnExit = (Button)findViewById(R.id.exit_button);           	//creating click listeners        	btnContinue.setOnClickListener(new View.OnClickListener() {              		@Override        		public void onClick(View v) {        			// -1 means that Game Activity will load all its data from        			// Preferences        			startGame(-1);        		}        	});               	btnNewGame.setOnClickListener(new View.OnClickListener() {             		@Override        		public void onClick(View v) {        			// user needs to select a difficulty level        			new AlertDialog.Builder(HangManActivity.this).setTitle("Select Difficulty")        			.setItems(R.array.difficulty, new DialogInterface.OnClickListener()        			{        				public void onClick(DialogInterface dialoginterface, int i) {        					// calling startGame function which will lauch Game with        					//appropriate difficulty level        					startGame(i);        				}        			})        			.show();        		}        	});             	btnAbout.setOnClickListener(new View.OnClickListener() {        		@Override        		public void onClick(View v) {        			Intent aboutIntent = new Intent(HangManActivity.this,About.class);        			startActivity(aboutIntent);        		}        	});            	btnExit.setOnClickListener(new View.OnClickListener() {        		@Override        		public void onClick(View v) {        			finish();        		}        	});        }         	private void startGame(int i) {        		Intent intent = new Intent(HangManActivity.this, Game.class);        		intent.putExtra(Game.KEY_DIFFICULTY, i);        		startActivity(intent);        	}         
}          