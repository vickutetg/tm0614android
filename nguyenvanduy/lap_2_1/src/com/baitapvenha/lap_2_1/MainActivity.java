package com.baitapvenha.lap_2_1;

import com.baitapvenha.lap_2_1.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener,OnCheckedChangeListener{
	
	Button btn_red,btn_green,btn_yellow;
	TextView txt_color;
	RadioGroup radio_group;
	RadioButton rd_red,rd_green,rd_yellow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		btn_red = (Button)findViewById(R.id.btn_red);
		btn_green = (Button)findViewById(R.id.btn_green);
		btn_yellow = (Button)findViewById(R.id.btn_yellow);
		txt_color = (TextView)findViewById(R.id.txt_color);
		radio_group = (RadioGroup)findViewById(R.id.radio_group);
		
		rd_red = (RadioButton)findViewById(R.id.rd_red);
		rd_green = (RadioButton)findViewById(R.id.rd_green);
		rd_yellow = (RadioButton)findViewById(R.id.rd_yellow);
		
		rd_red.setOnCheckedChangeListener(this);
		rd_green.setOnCheckedChangeListener(this);
		rd_yellow.setOnCheckedChangeListener(this);
		
		btn_red.setOnClickListener(this);
		btn_green.setOnClickListener(this);
		btn_yellow.setOnClickListener(this);
		
		//evenCheckedRadioButton();
		
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){		
		case R.id.btn_red:
			txt_color.setBackgroundColor(Color.RED);
			break;
		case R.id.btn_green:
			txt_color.setBackgroundColor(Color.GREEN);
			break;
		case R.id.btn_yellow:
			txt_color.setBackgroundColor(Color.YELLOW);
			break;
		}
		
	}
	
	/*public void evenCheckedRadioButton()
	{
		int isChecked = radio_group.getCheckedRadioButtonId();
		
		switch(isChecked){		
		case R.id.rd_red:
			txt_color.setBackgroundColor(Color.RED);
			break;
		case R.id.rd_green:
			txt_color.setBackgroundColor(Color.GREEN);
			break;
		case R.id.rd_yellow:
			txt_color.setBackgroundColor(Color.YELLOW);
			break;
		}
	}*/

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(isChecked){
			if((RadioButton)buttonView == rd_red){
				txt_color.setBackgroundColor(Color.RED);
			}
			if((RadioButton)buttonView == rd_green){
				txt_color.setBackgroundColor(Color.GREEN);
			}
			if((RadioButton)buttonView == rd_yellow){
				txt_color.setBackgroundColor(Color.YELLOW);
			}
		}
		
	}	
	
}
