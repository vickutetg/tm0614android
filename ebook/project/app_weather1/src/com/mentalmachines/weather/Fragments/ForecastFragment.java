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
import com.mentalmachines.weather.FetchJSONTask;
import com.mentalmachines.weather.ForecastResponse;
import com.mentalmachines.weather.LocationTask;
import com.mentalmachines.weather.R;

public class ForecastFragment extends Fragment{
	ForecastResponse forecastResponse = new ForecastResponse();
	String location; 
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		try {
			location = (String) new LocationTask().execute(null, "", "").get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonToObject();
		displayData();
		super.onStart();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_forecast_weather, container, false);
        return view;
	}
	
	private void JsonToObject(){
		Gson gson = new Gson();
		String jsonString = null;
		
		try {
			jsonString = new FetchJSONTask().execute(getURL("London, UK"), "", "").get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		forecastResponse = gson.fromJson(jsonString, ForecastResponse.class);
	}

	private String getURL(String location){
		Uri.Builder builder = new Uri.Builder();
		builder.scheme("https").authority("api.openweathermap.org").appendPath("data").appendPath("2.5").appendPath("forecast")
			.appendQueryParameter("q", location).appendQueryParameter("mode", "json");
		return builder.build().toString();
	}
	
	private void displayData(){
		
	}
}