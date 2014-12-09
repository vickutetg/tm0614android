package com.hoangphan.tutor1101_file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

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
	
	public void loadTextFromFile(View v){
		//init reader/inputstream
		String path = Environment.getExternalStorageDirectory().getPath();
		String filePath = path+"/hello.txt";
		Log.d("path", filePath);
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(filePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//read byte, buffer
		//eofFile = false, readline = null, =>eofFlag = true
		boolean eof = false;
		String readed = "";
		while (!eof) {
			try {
				String line = reader.readLine();
				if(line == null){
					eof = true;
				} else {
					readed += line + "\n";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//display in UI
		edtHello.setText(readed);
	}
	
	public void saveTextToFile(View v){
		//save external
		try {
			FileWriter writer = new FileWriter(new File("/sdcard/test"));
			String hello = edtHello.getText().toString();
			writer.write(hello+"\n");
			
			int progress = skFont.getProgress();
			char[] buf = new char[2];
			buf[0]= (char)(((int)'0')+progress); // '16'
			buf[1]= 'X';
			writer.write(buf);
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//save internal
		try {
			OutputStream out = openFileOutput("test", MODE_PRIVATE);
			BufferedWriter buffWr = new BufferedWriter(new OutputStreamWriter(out));
			buffWr.write("Hello, how are you.\n");
			buffWr.write("32");
			
			buffWr.flush();
			out.close();
			buffWr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Toast.makeText(this, "Save ok", Toast.LENGTH_LONG).show();
	}
}
