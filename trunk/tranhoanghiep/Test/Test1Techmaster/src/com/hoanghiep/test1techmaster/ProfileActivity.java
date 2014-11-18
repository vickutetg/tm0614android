package com.hoanghiep.test1techmaster;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ProfileActivity extends Activity {

	Button btnBook;
	Button btnProfile;
	Button btnExit;
	Button btnSend;
	EditText edtHoten;
	EditText edtCMND;
	EditText editBS;
	Toast toast;
	CheckBox checkboxDS;
	CheckBox checkboxDB;
	CheckBox checkboxCD;
	RadioGroup radioGroup;
	RadioButton radioTC;
	RadioButton radioCD;
	RadioButton radioDH;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		btnSend = (Button) findViewById(R.id.btnSend);
		btnExit = (Button) findViewById(R.id.btnExit);
		edtHoten = (EditText) findViewById(R.id.editHoten);
		edtCMND = (EditText) findViewById(R.id.editCMND);
		checkboxDB = (CheckBox) findViewById(R.id.checkboxDB);
		checkboxDS = (CheckBox) findViewById(R.id.checkboxDS);
		checkboxCD = (CheckBox)findViewById(R.id.checkboxCD);
		radioTC = (RadioButton) findViewById(R.id.radioTC);
		radioCD = (RadioButton) findViewById(R.id.radioCD);
		radioDH = (RadioButton) findViewById(R.id.radioDH);
		editBS = (EditText)findViewById(R.id.editBS);
		btnExit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		btnSend.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String hoten = edtHoten.getText().toString();
				int CMND = Integer.parseInt(edtCMND.getText().toString());
				if (hoten.length() == 0) {
					toast = Toast.makeText(ProfileActivity.this,
							"Phải nhập họ tên", Toast.LENGTH_LONG);
					toast.show();
					return;
				}
				String sothich = "";
				if (checkboxDB.isChecked())
					sothich += checkboxDB.getText() + "\n";
				if (checkboxDS.isChecked())
					sothich += checkboxDS.getText() + "\n";
				if (checkboxCD.isChecked())
					sothich += checkboxCD.getText() + "\n";
				String bangcap = "";
				if(radioTC.isChecked()){
					bangcap += "Trung cấp -";
				}
				if(radioDH.isChecked()){
					bangcap += "Đại học -";
				}
				if(radioCD.isChecked()){
					bangcap += "Cao đẳng";
				}
				String bosung = editBS.getText().toString();
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						ProfileActivity.this);
				dialog.setTitle("Thông tin cá nhân");
				dialog.setMessage(hoten + "\n" + CMND + "\n" + bangcap + "\n" + bangcap + "\n-------------\n" + "Thông tin bổ sung" + bosung);
				dialog.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});
				dialog.create().show();
			}
		});
	}
}
