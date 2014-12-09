package com.hoangphan.tutor1102_pref;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends Activity {

	private EditText edtHello;
	private SeekBar skFont;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		edtHello = (EditText) findViewById(R.id.edtHello);
		skFont = (SeekBar) findViewById(R.id.skFont);
		
		int fontSize = 16;
		skFont.setProgress(fontSize);
		edtHello.setTextSize(fontSize);
		edtHello.setText("Hello");
		
		//callback function
		skFont.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				edtHello.setTextSize(progress);
				Log.d("progress", progress+"");
			}
		});		
	}
	
	public void saveTextToFile(View v){
		//pref (init)
		SharedPreferences pref = this.getSharedPreferences("test", MODE_PRIVATE);
		
		//editor (writer)
		Editor editor = pref.edit();
		String helloMes = edtHello.getText().toString();
		editor.putString("hello", helloMes);
		
		int progress = skFont.getProgress();
		editor.putInt("size", progress);
		
		editor.commit();
	}
	
	  @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.setting, menu);
	    return true;
	  }

	@Override
	public boolean onMenuItemSelected(int menuId, MenuItem item) {
		return super.onMenuItemSelected(menuId, item);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
	        Intent i = new Intent(this, UserSettingActivity.class);
	        startActivity(i);
	        break;

		default:
			break;
		}
		
		return true;
	}
}
