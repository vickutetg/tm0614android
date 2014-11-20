package com.example.techmasterlab5_checkout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends Activity {

	ListView lvReceipt;
	Spinner spDay, spMonth, spYear;
	EditText edtCaptcha;

//	String[] days = { "1", "2", "3", "4", "5" };
	String[] days = arrayDate();
	String[] months = { "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" };
//	String[] years = { "2014", "2015", "2016", "2017", "2018" };
	String[] years = arrayYear();
	ArrayAdapter<String> adapter_Days;
	ArrayAdapter<String> adapter_Months;
	ArrayAdapter<String> adapter_Years;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lvReceipt = (ListView) this.findViewById(R.id.lvReceipt);

		ReceiptAdapter adapter = new ReceiptAdapter(this);
		lvReceipt.setAdapter(adapter);

		// khai bao spinner
		spDay = (Spinner) this.findViewById(R.id.spDay);
		spMonth = (Spinner) this.findViewById(R.id.spMonth);
		spYear = (Spinner) this.findViewById(R.id.spYear);

		adapter_Days = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, days);
		adapter_Months = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, months);
		adapter_Years = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, years);

		adapter_Days
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter_Months
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter_Years
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spDay.setAdapter(adapter_Days);
		spMonth.setAdapter(adapter_Months);
		spYear.setAdapter(adapter_Years);
		
		//Captcha
		edtCaptcha = (EditText) this.findViewById(R.id.edtCaptcha);
		
		edtCaptcha.setText(generateCaptchaString());
		
	}
	
	/**
	 *  Generate a CAPTCHA String consisting of random lowercase & uppercase letters, and numbers.
	 */
	public String generateCaptchaString() {
		Random random = new Random();
//		int length = 7 + (Math.abs(random.nextInt()) % 3);
		int length = 6;

		StringBuffer captchaStringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int baseCharNumber = Math.abs(random.nextInt()) % 62;
			int charNumber = 0;
			if (baseCharNumber < 26) {
				charNumber = 65 + baseCharNumber;
			}
			else if (baseCharNumber < 52){
				charNumber = 97 + (baseCharNumber - 26);
			}
			else {
				charNumber = 48 + (baseCharNumber - 52);
			}
			captchaStringBuffer.append((char)charNumber);
		}

		return captchaStringBuffer.toString();
	}
	
	public String nextCurrentDate(int numDays) {
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, numDays);
		String nextDate = dateFormat.format(cal.getTime());
		return nextDate;
	}
	
	public String[] arrayDate(){
		String[] arr = new String[7];
		
		for(int i = 0; i < 7; i++ ){
			arr[i] = nextCurrentDate(i);
			
		}
		
		return arr;
	}
	
	public String nextCurrentYear(int numDays) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, numDays);
		String nextDate = dateFormat.format(cal.getTime());
		return nextDate;
	}
	
	public String[] arrayYear(){
		String[] arr = new String[8];
		for(int i = 0; i < 8; i++ ){
			arr[i] = nextCurrentYear(i-1);
			
		}
		
		return arr;
	}
}
