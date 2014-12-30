package com.mentalmachines.weather;

import java.util.ArrayList;

public class ForecastResponse {
	String cod;
	Double message;
	public class City{
		Integer id;
		String name;
		Coordinates coord;
		String country;
		Integer population;
	}

	Integer cnt;
	ArrayList<ForecastListItem> list = new ArrayList<ForecastListItem>();
			
	public class ForecastListItem{
		int dt;
		Main main;
		ArrayList<Weather> weather;
		Clouds clouds;
		Wind wind;
		Sys sys;
		String dt_txt;
	}
	
	private class Coordinates{
		Double lon, lat;
	} 
	
	public class Main{
		Double temp, temp_min, temp_max, 
			pressure, sea_level, grnd_level;
		Integer humidity;
		Double temp_kf;
	}
	
	private class Sys{
		Double message;
		String country;
		String sunrise, sunset; 
	}
	
	Sys sys;

	private class Weather{
		Integer id;
		String main;
		String description;
		String icon;
	}
	
	public class Wind {
		Double speed;
		Integer deg;
	}
	
	public class Rain {
		Double threeh;
	}
	
	private class Clouds{
		Integer all;
	}
}
