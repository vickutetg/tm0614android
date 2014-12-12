package com.NguyenTruong.fodi;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.NguyenTruong.model.Food;

import android.util.Log;

public class XMLPullParserHandler {
	ArrayList<Food> foods;
	private Food food;
	private String text;

	public XMLPullParserHandler() {
		foods = new ArrayList<Food>();
	}

	public List<Food> getEmployees() {
		return foods;
	}

	public ArrayList<Food> parse(InputStream is) {
		XmlPullParserFactory factory = null;
		XmlPullParser parser = null;
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			parser = factory.newPullParser();

			parser.setInput(is, null);

			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String tagname = parser.getName();
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if (tagname.equalsIgnoreCase("Food")) {
						// create a new instance of Food
						food = new Food();
					}
					break;

				case XmlPullParser.TEXT:
					text = parser.getText();
					Log.d("test", text);
					break;

				case XmlPullParser.END_TAG:
					if (tagname.equalsIgnoreCase("Food")) {
						// add food object to list
						foods.add(food);
						Log.d("size", foods.size() + "");
					} else if (tagname.equalsIgnoreCase("Name")) {
						food.setName(text);
					} else if (tagname.equalsIgnoreCase("Dificult")) {
						food.setDificult(text);
					} else if (tagname.equalsIgnoreCase("Duration")) {
						food.setDuration(Integer.parseInt(text));
					} else if (tagname.equalsIgnoreCase("Material")) {
						food.setMaterial(text);
					} else if (tagname.equalsIgnoreCase("Cook")) {
						food.setCook(text);
					} else if (tagname.equalsIgnoreCase("ImageSource")) {
						food.setImage(text);
					} 
					break;

				default:
					break;
				}
				eventType = parser.next();
			}

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return foods;
	}
}
