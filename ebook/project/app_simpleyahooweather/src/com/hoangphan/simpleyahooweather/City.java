package com.hoangphan.simpleyahooweather;

public class City {
	private int mId;
	private String mName;
	
	public City (int id, String name) {
		this.mId = id;
		this.mName = name;
	}
	
	public int getId () {
		return mId;
	}
	
	public String getName () {
		return mName;
	}
}
