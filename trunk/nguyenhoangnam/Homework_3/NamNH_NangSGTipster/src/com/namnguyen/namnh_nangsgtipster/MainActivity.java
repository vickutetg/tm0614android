package com.namnguyen.namnh_nangsgtipster;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

	private ArrayList<Drink> drinkList;
	private ArrayAdapter<Drink> drinkAdp;
	private ArrayList<Float> total; 
	private float price;
	private float totalFee;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		price = 0;
		totalFee = 0;
		drinkList = new ArrayList<Drink>();
		drinkAdp = new ArrayAdapter<Drink>(getBaseContext(), android.R.layout.simple_spinner_item, drinkList);
		
		// another kind of drop down
		//drinkAdp.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		
		// create data
		createDrink();
		
		// ui
		Spinner spnDrink = (Spinner) findViewById(R.id.spnDrink);
		spnDrink.setAdapter(drinkAdp);
		
		// adding listener for spiner
		spnDrink.setOnItemSelectedListener(new SpinnerActivity());
		
		// EditText
		EditText edtQty = (EditText) findViewById(R.id.edtQty);
		edtQty.setText("0");
		EditText edtVAT = (EditText) findViewById(R.id.edtVAT);
		edtVAT.setText("10%");
		edtVAT.setEnabled(false);
		EditText edtTotal = (EditText) findViewById(R.id.edtTotal);
		edtTotal.setEnabled(false);
		
		// Button
		Button btnMinus = (Button) findViewById(R.id.btnMinus);
		Button btnPlus = (Button) findViewById(R.id.btnPlus);
		Button btnCalculate = (Button) findViewById(R.id.btnCalculate);
		
		btnMinus.setOnClickListener(new ButtonActivity());
		btnPlus.setOnClickListener(new ButtonActivity());
		btnCalculate.setOnClickListener(new ButtonActivity());
	}
	
	private void createDrink() {
		Drink beer = new Drink("Beer", 10);
		Drink juice = new Drink("Juice", 20);
		Drink softdrink = new Drink("Soft-Drink", 5);
		
		drinkList.add(beer);
		drinkList.add(juice);
		drinkList.add(softdrink);
	}
	
	// Inner class for Listener
	// For Spinner
	class SpinnerActivity implements OnItemSelectedListener {
		 
		public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
			Drink drink = (Drink) parent.getItemAtPosition(pos);
			price = drink.getPrice();
		}
	 
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}
	
	// For Button
	class ButtonActivity implements OnClickListener {

		@Override
		public void onClick(View v) {
			int qty = Integer.parseInt(((EditText)findViewById(R.id.edtQty)).getText().toString());
			switch (v.getId()) {
			case R.id.btnMinus:
				qty = qty - 1;
				break;
			case R.id.btnPlus:
				qty = qty + 1;
				break;
			case R.id.btnCalculate:
				totalFee = totalFee + price * qty + (price * qty) * 10 / 100;
				((EditText) findViewById(R.id.edtTotal)).setText("" + totalFee);			
				break;
			default:
				break;
			}
			((EditText)findViewById(R.id.edtQty)).setText(""+qty);
		}
	}
}

