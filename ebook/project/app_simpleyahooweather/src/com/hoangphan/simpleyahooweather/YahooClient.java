package com.hoangphan.simpleyahooweather;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;


public class YahooClient extends AsyncTask<String, Integer, String> {

	public YahooClient () {}
	
	@Override
    protected void onPreExecute() {
      super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... urls) {
    	try {
            String link = (String) urls[0];
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader
            (is, "UTF-8") );
            String data = null;
            String connResult = "";
            while ((data = reader.readLine()) != null) {
               connResult += data + "\n";
            }
            return connResult;
         } catch(Exception e) {
            return new String("Exception: " + e.getMessage());
         }
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
      super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute (String result) {
      super.onPostExecute(result);
    }
	
}