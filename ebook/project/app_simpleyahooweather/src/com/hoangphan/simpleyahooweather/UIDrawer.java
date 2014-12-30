package com.hoangphan.simpleyahooweather;


import java.util.List;
import java.util.concurrent.ExecutionException;

import com.hoangphan.simpleyahooweather.WeatherInfo.Forecast;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class UIDrawer {
	private Activity mActivity;
	private DbConnection mDbConn;
	private WeatherInfo mWeatherInfo;
	private TextView openAddCityWindow;
	private TextView addCity;
	private RelativeLayout addCityWrapper;
	private LinearLayout weatherInformatonLayout; 
	private View.OnClickListener openAddCityWindowListener;
	private View.OnClickListener addCityListener;
	private View.OnClickListener openWeatherInformationListener;
	
	public UIDrawer (Activity activity, DbConnection dbConn) {
		this.mActivity = activity;
		this.mDbConn = dbConn;
		this.mWeatherInfo = null;
		
		initLayouts();
		
		initListeners();
		
		setListeners();
	}
	
	private void initListeners () {
		this.openAddCityWindowListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addCityWrapper.setVisibility(View.VISIBLE);
			}
		};
		this.addCityListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = ((EditText) mActivity.findViewById(R.id.name)).getText().toString();
				String id = ((EditText) mActivity.findViewById(R.id.cityId)).getText().toString();
				if (name.equals("") || id.equals("")) {
					Toast toast = Toast.makeText(mActivity.getApplicationContext(), 
							   "Fill in all fields!", Toast.LENGTH_SHORT); 
					toast.show(); 
				} else {
					int cityId = Integer.parseInt(id);
					mDbConn.addCity(name, cityId);
					addCityElem(cityId, name);
					addCityWrapper.setVisibility(View.INVISIBLE);
				}
			}
		};
		this.openWeatherInformationListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int cityId = v.getId();
				if (isOnline()) {
					try {
						mWeatherInfo = new WeatherInfo(new YahooClient().execute("http://weather.yahooapis.com/forecastrss?w=" 
																				+ cityId + "&u=c").get());
						if (mWeatherInfo != null) {
							clearInformationLayout();
							fillInInformationLayout(v);
						} else {
							Toast toast = Toast.makeText(mActivity.getApplicationContext(), 
									   "Ooops, something go wrong!", Toast.LENGTH_SHORT);
							toast.show();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				} else {
					Toast toast = Toast.makeText(mActivity.getApplicationContext(), 
							   "No internet access!", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		};
	}
	
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	private void clearInformationLayout () {
		LinearLayout moreInfoLayout = (LinearLayout) mActivity.findViewById(R.id.moreInfo);
		moreInfoLayout.removeAllViews();
		for (int i = weatherInformatonLayout.getChildCount() - 1; i > 2; i--) {
			weatherInformatonLayout.removeViewAt(i);
		}
	}
	
	private void fillInInformationLayout (View v) {
		String cityName = ((TextView)((RelativeLayout) v).getChildAt(0)).getText().toString();	
		((TextView) mActivity.findViewById(R.id.cityName)).setText(cityName);
		((TextView) mActivity.findViewById(R.id.temperature)).setText(mWeatherInfo.getTodayTemp());
		LinearLayout moreInfoLayout = (LinearLayout) mActivity.findViewById(R.id.moreInfo);
		LayoutInflater inflater = mActivity.getLayoutInflater();
		TextView windTv = (TextView) inflater.inflate(R.layout.text_view_more_inf_elem_template, moreInfoLayout , false);
		windTv.setText("Wind* Chill:" + mWeatherInfo.getWind().getChill() + 
				" Direction: " + mWeatherInfo.getWind().getDir() + 
				" Speed: " + mWeatherInfo.getWind().getSpeed());
		TextView atmosphereTv = (TextView) inflater.inflate(R.layout.text_view_more_inf_elem_template, moreInfoLayout , false);
		atmosphereTv.setText("Atmosphere* Humidity:" + mWeatherInfo.getAtmosphere().getHumidity() + 
				" Visibility: " + mWeatherInfo.getAtmosphere().getVisibility() + 
				" Pressure: " + mWeatherInfo.getAtmosphere().getPressure() + 
				" Rising: " + mWeatherInfo.getAtmosphere().getRising());
		TextView astronomyTv = (TextView) inflater.inflate(R.layout.text_view_more_inf_elem_template, moreInfoLayout , false);
		astronomyTv.setText("Astronomy* Sunrise:" + mWeatherInfo.getAstronomy().getSunrise() + 
				" Sunset: " + mWeatherInfo.getAstronomy().getSunset());
		moreInfoLayout.addView(windTv);
		moreInfoLayout.addView(atmosphereTv);
		moreInfoLayout.addView(astronomyTv);
		List<Forecast> nextDays = mWeatherInfo.getNextDaysTemp();
		for (int i = 0; i < nextDays.size(); i++) {
			TextView nextDay = (TextView) inflater.inflate(R.layout.text_view_other_days_template, weatherInformatonLayout , false);
			nextDay.setText(nextDays.get(i).getDay() + ":    max: " + 
					nextDays.get(i).getTempMax() + "  min: " + nextDays.get(i).getTempMin());
			weatherInformatonLayout.addView(nextDay);
		}
//		weatherInformatonLayout.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//			}
//		});
		((ScrollView) weatherInformatonLayout.getParent()).setVisibility(View.VISIBLE);
	}
	
	private void initLayouts () {
		this.openAddCityWindow = (TextView) mActivity.findViewById(R.id.addCity);
		this.addCity = (TextView) mActivity.findViewById(R.id.add);
		this.addCityWrapper = (RelativeLayout) mActivity.findViewById(R.id.addCityWrapper);
		this.weatherInformatonLayout = (LinearLayout) mActivity.findViewById(R.id.information);
	}
	
	private void setListeners () {
		openAddCityWindow.setOnClickListener(openAddCityWindowListener);
		addCity.setOnClickListener(addCityListener);
	}
	
	public void drawCityList () {
		List<City> cities = mDbConn.getCities();
		
		for (int i = 0; i < cities.size(); i++) {
			addCityElem(cities.get(i).getId(), cities.get(i).getName());
		}
	}
	
	private void addCityElem (int id, String name) {
		LinearLayout mainContent = (LinearLayout) mActivity.findViewById(R.id.main);
		LayoutInflater inflater = mActivity.getLayoutInflater();
		RelativeLayout cityElem = (RelativeLayout) inflater.inflate(R.layout.list_elem_template, mainContent , false);
		TextView cityName = (TextView) cityElem.getChildAt(0);
		cityElem.setId(id);
		cityName.setText(name);
		cityElem.setOnClickListener(openWeatherInformationListener);
		mainContent.addView(cityElem, 0);
	}
}
