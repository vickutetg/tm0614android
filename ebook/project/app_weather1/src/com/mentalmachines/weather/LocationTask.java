package com.mentalmachines.weather;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

public class LocationTask extends AsyncTask implements LocationListener {
	private Location location;

	@Override
	protected Object doInBackground(Object... arg0) {
		LocationManager lm = (LocationManager) arg0[0];
		Looper.prepare();
		// Request GPS updates. The third param is the looper to use, which defaults the the one for
		// the current thread.
		lm.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
		Looper.loop(); // start waiting...when this is done, we'll have the location in this.location
		try {
			// now go use the location to load some data
			URL url = new URL("...?latitude="+location.getLatitude()+"&longitude="+location.getLongitude());
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();
			byte[] data = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = -1;
			do {
				len = is.read(data);
				if (len > 0) {
					baos.write(data, 0, len);
				}
			} while (len > -1);

			// parse data and do stuff...

		} catch (IOException e) {
			Log.e("LoadScheduleTask", "Error", e);
		}

		return null;
	}

	@Override
	protected void onPostExecute(Object result) {
		// notify someone we are done...
	}

	@Override
	public void onLocationChanged(Location location) {
		// Store the location, then get the current thread's looper and tell it to
		// quit looping so it can continue on doing work with the new location.
		this.location = location;
		Looper.myLooper().quit();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
