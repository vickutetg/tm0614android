package ducky.edu.weather;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WeatherHandler extends DefaultHandler {

	private String date;
	private String tempF;
	private String tempC;
	private String humidity;
	private String condition;
	private String icon;
	private ArrayList<String> dayList = new ArrayList<String>();
	private ArrayList<String> iconList = new ArrayList<String>();
	private ArrayList<String> conditionList = new ArrayList<String>();

	private int order = 0;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		String data = "";
		if (localName.equals("forecast_date")) {
			setDate(attributes.getValue("data"));
		} else if (localName.equals("temp_f")) {
			setTempF(attributes.getValue("data") + " °F");
		} else if (localName.equals("temp_c")) {
			setTempC(attributes.getValue("data") + " °C ");
		} else if (localName.equals("humidity")) {
			setHumidity(attributes.getValue("data"));
		} else if (localName.equals("forecast_conditions")) {
			order = 1;
		} else if (localName.equals("icon")) {
			data = attributes.getValue("data");
			if (order == 0) {
				setIcon(data);
			} else {
				iconList.add(data);
			}
		} else if (localName.equals("day_of_week")) {
			if (order != 0) {
				dayList.add(getWeekDay(attributes.getValue("data")));
			}
		} else if (localName.equals("condition")) {
			data = attributes.getValue("data");
			if (order == 0) {
				setCondition(data);
			} else {
				System.out.println(" Error Test condition != 0");
				conditionList.add(data);
			}
		}
	}

	private String getWeekDay(String day) {
		if (day.equals("Mon")) {
			return "Monday";
		} else if (day.equals("Tue")) {
			return "Tuesday";
		} else if (day.equals("Wed")) {
			return "Wednesday";
		} else if (day.equals("Thu")) {
			return "Thursday";
		} else if (day.equals("Fri")) {
			return "Friday";
		} else if (day.equals("Sat")) {
			return "Saturday";
		} else {
			return "Sunday";
		}

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTempF() {
		return tempF;
	}

	public void setTempF(String tempF) {
		this.tempF = tempF;
	}

	public String getTempC() {
		return tempC;
	}

	public void setTempC(String tempC) {
		this.tempC = tempC;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public ArrayList<String> getDayList() {
		return dayList;
	}

	public void setDayList(ArrayList<String> dayList) {
		this.dayList = dayList;
	}

	public ArrayList<String> getIconList() {
		return iconList;
	}

	public void setIconList(ArrayList<String> iconList) {
		this.iconList = iconList;
	}

	public ArrayList<String> getConditionList() {
		return conditionList;
	}

	public void setConditionList(ArrayList<String> conditionList) {
		this.conditionList = conditionList;
	}

}
