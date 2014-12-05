package com.hoangphan.tutor1002_accujson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class Utils {
	
	public static String getTextFromUri(String uri) throws Exception {
		URL url = new URL(uri);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		//config
		con.setRequestMethod("GET");
		con.setRequestProperty("name", "Hoang");
		//http://google.com?name=hoang	
			
		InputStream is = con.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		StringBuilder builder = new StringBuilder();
	    String line = "";
	    while ((line = reader.readLine()) != null) {
	    		builder.append(line);
	    }
		  
	    String str = builder.toString();
	    Log.d("json", str);
		return str;
	}
	
}
