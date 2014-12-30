package com.mentalmachines.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.os.AsyncTask;

public class FetchJSONTask extends AsyncTask<String, String, String> {
	
	public String jsonResponse;
	
	protected String doInBackground(String... requestStrings) {
		HttpURLConnection connection = null;
		OutputStreamWriter wr = null;
		BufferedReader rd  = null;
		StringBuilder sb = null;
		String line = null;
		String result = null;

		URL serverAddress = null;

		try {
			serverAddress = new URL(requestStrings[0]);
			//set up out communications stuff
			connection = null;

			//Set up the initial connection
			connection = (HttpURLConnection)serverAddress.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setReadTimeout(100000);

			connection.connect();

			//get the output stream writer and write the output to the server
			//not needed in this example
			//wr = new OutputStreamWriter(connection.getOutputStream());
			//wr.write("");
			//wr.flush();

			//read the result from the server
			rd  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			sb = new StringBuilder();

			while ((line = rd.readLine()) != null)
			{
				sb.append(line + '\n');
			}

			result = sb.toString();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			//close the connection, set all objects to null
			connection.disconnect();
			rd = null;
			sb = null;
			wr = null;
			connection = null;
		}
		
		return result;
	}

	protected void onProgressUpdate(Integer... progress) {
		// TODO You are on the GUI thread, and the first element in 
		// the progress parameter contains the last progress
		// published from doInBackground, so update your GUI
	}

	protected void onPostExecute(String result) {
		jsonResponse = result;
	}
}
