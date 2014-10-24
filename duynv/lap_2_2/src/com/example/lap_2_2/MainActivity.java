package com.example.lap_2_2;

import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	Button btn_add, btn_sub, btn_calculate;
	Spinner spinner;
	EditText txt_quantity;
	TextView total;
	
	ArrayList<Product> arrayList=new ArrayList<Product>();
	ArrayAdapter<Product>adapter=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initData();
		
		getView();
		
		
	}

	

	private void getView() {
		// TODO Auto-generated method stub
		btn_add = (Button)findViewById(R.id.add);
		btn_sub = (Button)findViewById(R.id.sub);
		btn_calculate= (Button)findViewById(R.id.calculate);
		
		txt_quantity = (EditText)findViewById(R.id.txt_quantity);
		total = (TextView)findViewById(R.id.total);
		
		btn_add.setOnClickListener(this);
		btn_sub.setOnClickListener(this);
		btn_calculate.setOnClickListener(this);
		
		spinner = (Spinner)findViewById(R.id.spinner_drink);
		
		
		//Gán Data cho adapter
		adapter=new ArrayAdapter<Product>(this, android.R.layout.simple_spinner_item, arrayList);
		
		//Hiển thị danh sách cho spinner
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//Thiết lập adapter cho spinner
		spinner.setAdapter(adapter);	
		
	}
	
	
	/**
	 * Khởi tạo mảng dữ liệu arrayList
	 */
	private void initData() {
		// TODO Auto-generated method stub
		Product product = new Product("Coffee",30000);		
		arrayList.add(product);
		
		Product product1 = new Product("Coffee1",35000);		
		arrayList.add(product1);
		
		Product product2 = new Product("Coffee2",40000);		
		arrayList.add(product2);
		
		Product product3 = new Product("Coffee3",45000);		
		arrayList.add(product3);
		
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId())
		{
		
		case R.id.add:
			//Toast.makeText(MainActivity.this,"Error", Toast.LENGTH_LONG).show();
			operator(1);
			break;
			
		case R.id.sub:
			operator(-1);
			break;
		case R.id.calculate:
			calculate();
			break;
			
		}
		
	}
	
	
	private void calculate() {
		// TODO Auto-generated method stub
		
		//Lấy vị trí của arraylist đang được chọn tại spinner		
		Product product = arrayList.get(spinner.getSelectedItemPosition());
		
		//Lấy số lượng
		String x = txt_quantity.getText().toString();
		
		//Kiểm tra số lượng có phải là số ko 
		if(_checkNumber(x))
		{
			int quantity = Integer.parseInt(x);
			double price = product.getPrice();
			double sum = (price * quantity) + (price * quantity * 0.1);
			total.setText(sum+"");			
		}else{
			Toast.makeText(MainActivity.this, "Kiểm tra lại trường số lượng", Toast.LENGTH_LONG).show();
		}
		
		
		
	}



	/**
	 * Tính số lượng khi click vào button thêm hoặc bớt
	 * 
	 * @param _param = 1 nếu click vào nút thêm, _param = -1 nếu click vào nút bớt
	 */
	
	public void operator(int _param)
	{
		String x = txt_quantity.getText().toString();
		
		if(_checkNumber(x)){			
			int quantity = Integer.parseInt(x) + 1 * _param;
			
			Log.d("Log Quantity","Quantity = " + quantity);
			
			//Nếu số lượng <= 0 thì hiển thị ra thông báo, nút giảm số lượng setEnable = false;			
			if(quantity <= 0){
				Toast.makeText(MainActivity.this, "Số lượng phải lớn hơn 0", Toast.LENGTH_LONG).show();
				btn_sub.setEnabled(false);
			}else{
				txt_quantity.setText(quantity+"");
				btn_sub.setEnabled(true);
			}
			
			
		}else{
			Toast.makeText(MainActivity.this,"Error", Toast.LENGTH_LONG).show();
		}
	}	
	
	
	
	
	/**
	 * Check type data
	 * @param x
	 * @return boolean
	 */
	public boolean _checkNumber(String x)
	{		
		try{
			Integer.parseInt(x);
			
		}catch(Exception e){
			return false;
		}
		
		return true;
	}
}
