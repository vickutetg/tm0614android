package com.mentalmachines.weather;

import java.util.ArrayList;

public class CurrentResponse {
	Coordinates coord;
	public Sys sys;
	// Weather weather;
	ArrayList<Weather> weather;
	String base;
	
	public Main main;
	public Wind wind;
	public Rain rain;
	public Clouds clouds;

	Integer dt;
	Integer id;
	String name;
	Integer cod;
	
	private class Coordinates{
		public Double lon, lat;
	} 
	
	public class Sys{
		Double message;
		String country;
		public String sunrise;
		public String sunset; 
	}

	private class Weather{
		public Integer id;
		public String main;
		public String description;
		public String icon;
	}

	public class Main{
		public Double temp;
		public Integer pressure;
		public Integer humidity;
		public Double temp_min, temp_max;
	}
	
	public class Wind {
		public Double speed;
		public Double deg;
	}
	
	public class Rain {
		public Double threeh;
	}
	
	public class Clouds{
		public Integer all;
	}
}
