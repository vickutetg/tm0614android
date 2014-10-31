package com.hoanghiep.giaiphuongtrinh;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText editA;
	EditText editB;
	EditText editC;
	Button btnTinh;
	TextView txtKQ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editA = (EditText) findViewById(R.id.editA);
		editB = (EditText) findViewById(R.id.editB);
		editC = (EditText) findViewById(R.id.editC);
		txtKQ = (TextView) findViewById(R.id.txtKQ);
		btnTinh = (Button) findViewById(R.id.btnTinh);
		btnTinh.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				int a = Integer.parseInt(editA.getText().toString());
				int b = Integer.parseInt(editB.getText().toString());
				int c = Integer.parseInt(editC.getText().toString());
				
				double delta = (b*b)*(4*a*c);
				if(delta < 0){
					txtKQ.setText("Phuong trinh vo nghiem");
				} else if (delta == 0){
					double x = (-b)/(2*a);
					txtKQ.setText("Phuong trinh co nghiem kep x1=x2=" + x);
				}else{
					double x1 = (-b) + Math.sqrt(delta)/(2*a);
					double x2 = (-b) - Math.sqrt(delta)/(2*a);
					txtKQ.setText("Phuong trinh co nghiem\nx1 = " + x1 + "\nx2 = " + x2);
				}
			}
		});
	}
}
