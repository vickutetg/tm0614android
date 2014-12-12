package com.NguyenTruong.checkout;

import java.util.Calendar;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CheckoutActivity extends Activity {

	Calendar timeNow = Calendar.getInstance();
	int day = timeNow.get(Calendar.DATE);
	int month = timeNow.get(Calendar.MONTH) + 1;
	String year = timeNow.get(Calendar.YEAR) + "";
	int IntYear = timeNow.get(Calendar.YEAR);

	String[] arrDistrict1 = { "Long Biên", "Hoàn Kiếm" };
	String[] arrDistrict2 = { "Quận 1", "Quận 2" };

	String[] arrDay = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
			"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
			"22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

	String[] arrMonth = { "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December" };

	String[] arrYear = new String[10];

	String[] arrCity = { "Hà Nội", "TP Hồ Chí Minh" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkout);
		
		// tạo dữ liệu cho spinner Year
		for (int i = 0; i < 10; i++) {
			int count = IntYear + i;
			arrYear[i] = count + "";
		}
		
		
		//Khai báo các phần tử
		Spinner spnDay = (Spinner) findViewById(R.id.spnDay);
		Spinner spnMonth = (Spinner) findViewById(R.id.spnMonth);
		Spinner spnYear = (Spinner) findViewById(R.id.spnYear);
		Spinner spnCity = (Spinner) findViewById(R.id.spnCity);
		final Spinner spnDis = (Spinner) findViewById(R.id.spnDistrict);

		// tạo captcha
		TextView txtCaptcha = (TextView) findViewById(R.id.txtCaptcha);
		txtCaptcha.setText(captcha());

		// tạo spinner card valid
		ArrayAdapter<String> adapterDay = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, arrDay);

		adapterDay.setDropDownViewResource(android.R.layout.simple_list_item_1);

		spnDay.setAdapter(adapterDay);

		ArrayAdapter<String> adapterMonth = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, arrMonth);

		adapterMonth
				.setDropDownViewResource(android.R.layout.simple_list_item_1);

		spnMonth.setAdapter(adapterMonth);

		ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, arrYear);

		adapterYear
				.setDropDownViewResource(android.R.layout.simple_list_item_1);

		spnYear.setAdapter(adapterYear);

		spnDay.setSelection(day - 1, true);
		spnMonth.setSelection(month - 1, true);
		for (int i = 0; i < arrYear.length; i++) {
			if (year.equals(arrYear[i])) {
				spnYear.setSelection(i, true);
			}
		}

		// tao spinner city, district
		ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, arrCity);

		adapterCity
				.setDropDownViewResource(android.R.layout.simple_list_item_1);

		spnCity.setAdapter(adapterCity);

		spnCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					ArrayAdapter<String> adapterDistrict = new ArrayAdapter<String>(
							CheckoutActivity.this, android.R.layout.simple_spinner_item,
							arrDistrict1);

					adapterDistrict
							.setDropDownViewResource(android.R.layout.simple_list_item_1);

					spnDis.setAdapter(adapterDistrict);
				} else {
					ArrayAdapter<String> adapterDistrict = new ArrayAdapter<String>(
							CheckoutActivity.this, android.R.layout.simple_spinner_item,
							arrDistrict2);

					adapterDistrict
							.setDropDownViewResource(android.R.layout.simple_list_item_1);

					spnDis.setAdapter(adapterDistrict);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});


	}

	private String captcha() {
		String captcha = "";
		String str01 = "abcdefghijklmnopqrstuvwxyz";
		String str02 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String str03 = "0123456789";

		String strValid = str01 + str02 + str03;

		Random random = new Random();

		for (int i = 0; i < 6; i++) {
			int randnum = random.nextInt(strValid.length());
			captcha = captcha + strValid.charAt(randnum);
		}

		return captcha;

	}

}
