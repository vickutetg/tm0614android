package com.hoangphan.simpleyahooweather;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class WeatherInfo {
	
	private String mResponse;
	
	private String mLocation;
	private Atmosphere mAtmosphere;
	private Wind mWind;
	private Astronomy mAstronomy;
	private String mTodayTemp;
	private String mLastUpdate;
	private List<Forecast> mNextDaysTemp;
	
	
	public WeatherInfo (String response) {
		this.mResponse = response;
		
		this.mNextDaysTemp = new ArrayList<WeatherInfo.Forecast>();
		
		parseResponse();
		
	}
	
	public String getLocation () {
		return mLocation;
	}
	
	public String getTodayTemp () {
		return mTodayTemp;
	}
	
	public String getLastUpdate () {
		return mLastUpdate;
	}
	
	public Atmosphere getAtmosphere () {
		return mAtmosphere;
	}
	
	public Wind getWind () {
		return mWind;
	}
	
	public Astronomy getAstronomy () {
		return mAstronomy;
	}
	
	public List<Forecast> getNextDaysTemp () {
		return mNextDaysTemp;
	}
	
	private void parseResponse () {
		try {
	        XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
	        parser.setInput(new StringReader(mResponse));
	 
	        String tagName = null;
	        String currentTag = null;
	 	        
	        int event = parser.getEventType();
	        
	        while (event != XmlPullParser.END_DOCUMENT) {
	            tagName = parser.getName();
	 
	            if (event == XmlPullParser.START_TAG) {
	                if (tagName.equals("yweather:wind")) {
	                    mWind = new Wind(parser.getAttributeValue(null, "speed"), 
	                    		parser.getAttributeValue(null, "direction"), 
	                    		parser.getAttributeValue(null, "chill"));
	                } else if (tagName.equals("yweather:atmosphere")) {
	                    mAtmosphere = new Atmosphere(parser.getAttributeValue(null, "humidity"), 
	                    		parser.getAttributeValue(null, "visibility"), 
	                    		parser.getAttributeValue(null, "pressure"), 
	                    		parser.getAttributeValue(null, "rising"));
	                } else if (tagName.equals("yweather:forecast")) {
	                    Forecast f = new Forecast(parser.getAttributeValue(null, "day"),
	                    		parser.getAttributeValue(null, "low") + "Celcius", 
	                    		parser.getAttributeValue(null, "high") + "Celcius");
	                    mNextDaysTemp.add(f);
	                } else if (tagName.equals("yweather:condition")) {
	                    mTodayTemp = parser.getAttributeValue(null, "temp") + "Celcius";
	                } else if (tagName.equals("yweather:units")) {
	                   
	                } else if (tagName.equals("yweather:location")) {
	                    
	                } else if (tagName.equals("lastBuildDate")) {
	                   currentTag="update";
	                }
	                else if (tagName.equals("yweather:astronomy")) {
	                	mAstronomy = new Astronomy (parser.getAttributeValue(null, "sunrise"), parser.getAttributeValue(null, "sunset"));
	                }
	 
	            }
	            else if (event == XmlPullParser.TEXT) {
	                if ("update".equals(currentTag))
	                    mLastUpdate = parser.getText();
	            }
	            event = parser.next();
	        }
	 
	    }
	    catch(Throwable t) {
	        t.printStackTrace();
	    }
	 
	}
	
	public class Forecast {
		private String mDay;
		private String mTempMin;
		private String mTempMax;
		
		public Forecast (String day, String tempMin, String tempMax) {
			this.mDay = day;
			this.mTempMin = tempMin;
			this.mTempMax = tempMax;
		}
		
		public String getDay () {
			return mDay;
		}
		
		public String getTempMin () {
			return mTempMin;
		}
		
		public String getTempMax () {
			return mTempMax;
		}
	}
	
	public class Wind {
		private String mSpeed;
		private String mDirection;
		private String mChill;
		
		public Wind (String speed, String dir, String chill) {
			this.mChill = chill;
			this.mDirection = dir;
			this.mSpeed = speed;
		}
		
		public String getSpeed () {
			return mSpeed;
		}
		
		public String getDir () {
			return mDirection;
		}
		
		public String getChill () {
			return mChill;
		}
	}
	
	public class Astronomy {
		private String mSunrise;
		private String mSunset;
		
		public Astronomy (String rise, String set) {
			this.mSunrise = rise;
			this.mSunset = set;
		}
		
		public String getSunrise () {
			return mSunrise;
		}
		
		public String getSunset () {
			return mSunset;
		}
		
	}
	
	public class Atmosphere {
		private String mHumidity;
		private String mVisibility;
		private String mPressure;
		private String mRising;
		
		public Atmosphere (String hum, String vis, String press, String ris) {
			this.mHumidity = hum;
			this.mVisibility = vis;
			this.mPressure = press;
			this.mRising = ris;
		}
		
		public String getHumidity () {
			return mHumidity;
		}
		
		public String getVisibility () {
			return mVisibility;
		}
		
		public String getPressure () {
			return mPressure;
		}
		
		public String getRising () {
			return mRising;
		}
	}
}