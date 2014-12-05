package com.hoangphan.tutor1002_accujson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView celTxt;
	private TextView stateTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initUI();
	}
	
	private void initUI() {
		celTxt = (TextView) findViewById(R.id.cel);
		stateTxt = (TextView) findViewById(R.id.stat);
	}

	public void accuRead(View v) {
		ProgressDialog pgrDlg = new ProgressDialog(MainActivity.this);
		pgrDlg.setTitle("Wait me a litle to get data from acc");
		pgrDlg.show();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				//check internet connection

				//allow main thread access network
				//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				//StrictMode.setThreadPolicy(policy); 
				
				//display progress dialog (formal dialog)
				
				//read from acc
				String accUri = "http://apidev.accuweather.com/currentconditions/v1/1-353412_1_AL.json?language=en&apikey=meSocYcloNe";
				String json = null;
				try {
					json = Utils.getTextFromUri(accUri);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//parser json
				try {
					JSONArray weatherArr = new JSONArray(json);
					JSONObject weatherJson = (JSONObject) weatherArr.get(0);
					final String stateWeather = weatherJson.optString("WeatherText");
					JSONObject tempJSON = weatherJson.optJSONObject("Temperature");
					JSONObject metricJSON = tempJSON.optJSONObject("Metric");
					final String celWeather = metricJSON.optString("Value");
					
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							celTxt.setText(celWeather);
							stateTxt.setText(stateWeather);				
						}
					});
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		//hide progess
		pgrDlg.dismiss();
	}
}
