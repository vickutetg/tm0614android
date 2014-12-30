package com.mentalmachines.weather.Fragments;

import java.util.concurrent.ExecutionException;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mentalmachines.weather.CurrentResponse;
import com.mentalmachines.weather.FetchJSONTask;
import com.mentalmachines.weather.R;

public class CurrentFragment extends Fragment{
	TextView sunriseIdTextView, sunriseTextView, sunsetIdTextView,  sunsetTextView,
		tempIdTextView, tempTextView, pressureIdTextView, pressureTextView,
		humidityIdTextView, humidityTextView, tempMinIdTextView, tempMinTextView, 
		tempMaxIdTextView, tempMaxTextView, windIdTextView, windMaxTextView, 
		rainIdTextView, rainTextView, cloudsIdTextView, cloudsTextView;
	
	TextView titleTextView;  
	CurrentResponse current= new CurrentResponse();
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		jsonToObject();
		displayData();
		super.onStart();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_current_weather, container, false);
        
        titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        
		sunriseIdTextView = (TextView) view.findViewById(R.id.sunriseIdTextView);
		sunriseTextView = (TextView) view.findViewById(R.id.sunriseTextView);
		sunsetIdTextView = (TextView) view.findViewById(R.id.sunsetIdTextView);
		sunsetTextView = (TextView) view.findViewById(R.id.sunsetTextView);
		tempIdTextView = (TextView) view.findViewById(R.id.tempIdTextView); 
		tempTextView = (TextView) view.findViewById(R.id.tempTextView);
		pressureIdTextView = (TextView) view.findViewById(R.id.pressureIdTextView);
		pressureTextView = (TextView) view.findViewById(R.id.pressureTextView);
		humidityIdTextView = (TextView) view.findViewById(R.id.humidityIdTextView);
		humidityTextView = (TextView) view.findViewById(R.id.humidityTextView);
		tempMinIdTextView = (TextView) view.findViewById(R.id.tempMinIdTextView);
		tempMinTextView = (TextView) view.findViewById(R.id.tempMinTextView);
		tempMaxIdTextView = (TextView) view.findViewById(R.id.tempMaxIdTextView);
		tempMaxTextView = (TextView) view.findViewById(R.id.tempMaxTextView);
		windIdTextView = (TextView) view.findViewById(R.id.windIdTextView);
		windMaxTextView = (TextView) view.findViewById(R.id.windMaxTextView);
		rainIdTextView = (TextView) view.findViewById(R.id.rainIdTextView);
		rainTextView = (TextView) view.findViewById(R.id.rainTextView);
		cloudsIdTextView = (TextView) view.findViewById(R.id.cloudsIdTextView);
		cloudsTextView = (TextView) view.findViewById(R.id.cloudsTextView);
        return view;
	}
	
	private void jsonToObject(){
		Gson gson = new Gson();
		String jsonString = null;
		
		try {
			jsonString = new FetchJSONTask().execute(getURL("London,uk"), "", "").get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		current = gson.fromJson(jsonString, CurrentResponse.class);
	}

	private String getURL(String location){
		Uri.Builder builder = new Uri.Builder();
		String URL;
		builder.scheme("http").authority("api.openweathermap.org").appendPath("data").appendPath("2.5").appendPath("weather")
			.appendQueryParameter("q", location).appendQueryParameter("mode", "json");
		URL = builder.build().toString();
		return URL;
	}
	
	public void displayData(){
		sunriseTextView.setText(current.sys.sunrise);
		sunsetTextView.setText(current.sys.sunset);
		tempTextView.setText(String.valueOf(current.main.temp));
		pressureTextView.setText(Integer.toString(current.main.pressure));
		humidityTextView.setText(Integer.toString(current.main.humidity));
		tempMinTextView.setText(String.valueOf(current.main.temp_min));
		tempMaxTextView.setText(String.valueOf(current.main.temp_max));
		windMaxTextView.setText(String.valueOf(current.wind.speed));
		rainTextView.setText(String.valueOf(current.rain.threeh));
		cloudsTextView.setText(Integer.toString(current.clouds.all));
	} 
}