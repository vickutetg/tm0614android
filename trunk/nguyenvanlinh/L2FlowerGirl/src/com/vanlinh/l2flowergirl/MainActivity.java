package com.vanlinh.l2flowergirl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button btnBuy;
	private Button btnCheckOut;
	private TextView tvOrder;
	private EditText edtName;
	private String name = "";
	private long moneyEach = 1000L; // gia tien moi loai hoa
	private int count = 0;// so luong hoa
	private String dateTime;
	private long moneyTotal; // tong so tien phai tra
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //khoi tao cac widget
        edtName = (EditText) findViewById(R.id.edtName);
        btnBuy = (Button) findViewById(R.id.btnBuy);
        tvOrder = (TextView) findViewById(R.id.tvOrder);
        btnCheckOut = (Button) findViewById(R.id.btnCheckOut);
        
        //xu ly su kien an phim Buy
        btnBuy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String flower;
				flower = edtName.getText().toString().trim() ;
				if(flower.equals("")){
					return;
				}else{
					name = name + flower + "\n";
					tvOrder.setText(name);
					
					count ++;
					
			        Toast.makeText(MainActivity.this, flower + " add to order successfully."
			        		+ "I will contact with you sooner", Toast.LENGTH_LONG).show();;
			        
			        
				}
				
				edtName.setText("");
			}
			
		});
        
        //xu ly su kien check out
        btnCheckOut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//tinh tien 
				moneyTotal = moneyEach * count;
				
				//thoi gian
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				//get current date time with Date()
				Date date = new Date();
				dateTime = dateFormat.format(date);
				
				//khoi tao 1 alertdialog
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("Receipt for flower");
				String message = "Thank you for your order of my flowers.\n"
						+ "Money you must pay is: " + moneyTotal + "$.\n"
								+ dateTime  + ".\n"
										+ "If I do not received money on";
				builder.setMessage(message);
				
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
					
				});
				//hien thi alertdialog
				builder.create().show();
			}
		});
        
    }


    
}
