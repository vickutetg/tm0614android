package com.NguyenTruong.fodi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.NguyenTruong.adapter.FoodAdapterList;
import com.NguyenTruong.model.Food;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	public static final int REQUEST_CODE_INPUT=1;
	public static final int RESULT_CODE_SAVE=2;
	ListView listView;
	ArrayList<Food> arrFood;
	ArrayList<Food> listSearch;
	Button btnsearch,btnList;
	FoodAdapterList adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		arrFood = new ArrayList<Food>();
		listView = (ListView) findViewById(R.id.lvFood);
		btnsearch = (Button) findViewById(R.id.btnSearch);
		btnList = (Button) findViewById(R.id.btnList);
		
		try {
			XMLPullParserHandler parser = new XMLPullParserHandler();
			arrFood = parser.parse(getAssets().open("data.xml"));
			Log.d("testMail", arrFood.size()+"");
			adapter = new FoodAdapterList(this, arrFood);

			listView.setAdapter(adapter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(MainActivity.this, FoodDetailActivity.class);
				i.putExtra("food", arrFood.get(position));
				startActivity(i);
			}
		});
		
		btnsearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, SearchActivity.class);
				startActivityForResult(i,REQUEST_CODE_INPUT);
			}
		});

		
		btnList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listView.setAdapter(adapter);
				btnList.setVisibility(View.INVISIBLE);
			}
		});
		

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode==REQUEST_CODE_INPUT){
			listSearch = new ArrayList<Food>();
			
			String foodname = data.getStringExtra("data");
			Log.d("data", foodname.toUpperCase());
			for (int i = 0; i < arrFood.size(); i++) {
				if(arrFood.get(i).getName().contains(foodname.toUpperCase())){
					Food fSearch = arrFood.get(i);
					Log.d("food", arrFood.get(i).getName());
					listSearch.add(fSearch);
				}
			}
			
		
			
			
			FoodAdapterList adapterSearch = new FoodAdapterList(this, listSearch);
			listView.setAdapter(adapterSearch);
			
			btnList.setVisibility(View.VISIBLE);
		}
		
		
		
	}
}
