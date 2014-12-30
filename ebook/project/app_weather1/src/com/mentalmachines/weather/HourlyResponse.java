package com.mentalmachines.weather;

public class HourlyResponse {

	private class Coordinates{
		Double lon, lat;
	} 
	Coordinates coord;
	
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
	Weather weather;
	
	String base;
	
	public class Main{
		Double temp;
		Integer pressure;
		Integer humidity;
		Double temp_min, temp_max;
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
	
	Integer dt;
	Integer id;
	String name;
	Integer cod;
	}

