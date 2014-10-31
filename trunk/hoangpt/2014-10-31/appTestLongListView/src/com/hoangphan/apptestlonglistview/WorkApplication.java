package com.hoangphan.apptestlonglistview;

import java.util.HashMap;

import android.app.Application;

public class WorkApplication extends Application {
	
	static HashMap<Integer, Integer> maps = new HashMap<>();
	
	static void setCheck(int position){
		maps.put(position, 1);
	}
	
	static int getCheck(int position){
		if(maps.get(position) == null){
			return 0;
		} else {
			return 1;
		}
	}
}
