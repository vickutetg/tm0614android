package com.NguyenTruong.fodi;

import java.io.IOException;
import java.io.InputStream;

import com.NguyenTruong.model.Food;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class FoodDetailActivity extends Activity {
	
	ImageView foodImg;
	TextView name,dificult,duration,material,cook;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_detail);
		Intent i = getIntent();
		Food food = new Food();
		food = (Food) i.getExtras().getSerializable("food");
		Log.d("intent", food.getName());
		
		name = (TextView) findViewById(R.id.txtFoodNameDetail);
		dificult = (TextView) findViewById(R.id.txtDificultDetail);
		duration = (TextView) findViewById(R.id.txtTimeDetail);
		material = (TextView) findViewById(R.id.txtMaterial);
		cook = (TextView) findViewById(R.id.txtCook);
		foodImg = (ImageView) findViewById(R.id.imgFoodDetail);
		
		name.setText(food.getName());
		dificult.setText(food.getDificult());
		duration.setText(food.getDuration()+" Phút");
		material.setText(food.getMaterial().trim());
		cook.setText(food.getCook().trim());
		
		
		InputStream ims;
		try {
			ims = getAssets().open(food.getImage());
			Bitmap d = BitmapFactory.decodeStream(ims);
			foodImg.setScaleType(ScaleType.FIT_XY);
			foodImg.setImageBitmap(d);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
