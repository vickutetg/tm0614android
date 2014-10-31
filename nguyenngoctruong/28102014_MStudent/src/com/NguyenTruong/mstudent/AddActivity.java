package com.NguyenTruong.mstudent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity {
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

		final EditText edtName = (EditText) findViewById(R.id.edtName);
		final EditText edtHoa = (EditText) findViewById(R.id.edtHoa);
		final EditText edtLy = (EditText) findViewById(R.id.edtLy);
		final EditText edtToan = (EditText) findViewById(R.id.edtToan);

		final ArrayList<Student> arrList = new ArrayList<Student>();
		
		Button btnAdd = (Button) findViewById(R.id.btnAdd);

		
		//tao event button add
		
		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Student st = new Student();
				float hoa = Float.parseFloat(edtHoa.getText().toString());
				float ly = Float.parseFloat(edtLy.getText().toString());
				float toan = Float.parseFloat(edtHoa.getText().toString());
				st.setName(edtName.getText().toString());
				st.setHoa(hoa);
				st.setLy(ly);
				st.setToan(toan);
				st.setTb(toan, ly, hoa);
				arrList.add(st);
				Toast.makeText(AddActivity.this,
						edtName.getText().toString() + " đã được thêm",
						Toast.LENGTH_LONG).show();

				edtHoa.setText("");
				edtToan.setText("");
				edtLy.setText("");
				edtName.setText("");
				
				
				
				// ghi du lieu vao file txt
				try {
					FileOutputStream fo = openFileOutput("dulieu.txt",MODE_APPEND);

					
					String student = st.getName() + ":" + st.getToan()+ ":" + st.getLy() + ":" + st.getHoa() + "\t";
					fo.write(student.getBytes());
			
					fo.close();

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					Toast.makeText(AddActivity.this, "LỖi", Toast.LENGTH_LONG)
							.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				

			}
		});

		
		
		// tao event button list
		Button btnList = (Button) findViewById(R.id.btnList);
		btnList.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				
				Intent i = new Intent(AddActivity.this, ListActivity.class);
				startActivity(i);

			}
		});

	}

}
