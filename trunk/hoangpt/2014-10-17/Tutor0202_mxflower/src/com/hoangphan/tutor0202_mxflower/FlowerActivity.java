package com.hoangphan.tutor0202_mxflower;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FlowerActivity extends Activity {

	/**
	 * global
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flower); //set UI vào app.
		
		//đọc các UI ra
		Button btnAddOrder = (Button) findViewById(R.id.btnAddOrder);
		final EditText edtFLowerName = (EditText) findViewById(R.id.edtFLowerName);
		final TextView txtOrder = (TextView) findViewById(R.id.txtOrder);
		
		//listener khi ấn nút button
		btnAddOrder.setOnClickListener(new View.OnClickListener() {
			/**
			 * callback
			 */
			@Override
			public void onClick(View v) {
				//đọc tên hoa
				String flowerName = edtFLowerName.getText().toString();
				
				//nhét thêm vào order
				String before = txtOrder.getText().toString();
				String after = before + "\n"; //dấu xuống dòng 
				after = after + flowerName;
				txtOrder.setText(after); //text mới ở chỗ order
				
				//Log vao con mèo L
				Log.d("hoa", flowerName);
				System.out.println("flowerName");
				
				//hiển thị thông báo
				Toast.makeText(FlowerActivity.this, 
						"Bạn đã mua "+flowerName+" này thành công", Toast.LENGTH_LONG)
					.show();	
				
				//cho ô nhập tên về blank
				edtFLowerName.setText("");
			}
		});
		
	}
}
