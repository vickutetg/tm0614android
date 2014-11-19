package com.example.duynv_test01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	
	Button btn_login,btn_exit;
	EditText edt_mail, edt_pass;
	CheckBox ck_save;
	String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	String prefname="my_data";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_login = (Button)findViewById(R.id.btn_login);
		btn_exit = (Button)findViewById(R.id.btn_exit);
		
		edt_mail = (EditText)findViewById(R.id.edt_mail);
		edt_pass = (EditText)findViewById(R.id.edt_pass);
		
		ck_save = (CheckBox)findViewById(R.id.ck_save);
		
		btn_login.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{
		case R.id.btn_login:
			login();
			break;
		case R.id.btn_exit:
			quit();
			break;
		
		}
		
	}


	private void quit() {
		// TODO Auto-generated method stub
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);		 
		alertDialog.setTitle("Question");
		alertDialog.setMessage("Are you sure you want to exit?");
		
		alertDialog.setPositiveButton("Yes", new DialogInterface. OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which){
				
				finish();
			
			}
		});
		alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which){
		
					dialog.cancel();
		
				}
	
			});
		alertDialog.create().show();		
		
	}


	private void login() {
		// TODO Auto-generated method stub
		
		String mail = edt_mail.getText().toString();
		String pass = edt_pass.getText().toString();
		
		Boolean b = mail.matches(emailreg);
		Boolean flag = true;
		
		if(!b) {
			createDialog("Email không đúng định dạng ");
			flag = false;
			
		} else {
			if(pass.length() > 50 || pass.length() < 6){
				createDialog("Password phải có độ dài từ 6 - 255 ký tự ");
				flag = false;
			}
		}
		
		if(flag){
			finish();
			Intent i=new Intent(this, LoginSuccessActivity.class);
			startActivity(i);
		}
		
	}
	
	@Override
	protected void onPause() {
		
		super.onPause();
		
		//gọi hàm lưu trạng thái
		savingPreferences();
	}
	@Override
	protected void onResume() {
		
		super.onResume();
		//đọc trạng thái
		restoringPreferences();
	}
	
	
	/**
	 * Lưu trạng thái
	 */
	public void savingPreferences()
	{
		//tạo đối tượng getSharedPreferences
		SharedPreferences pre=getSharedPreferences(prefname, MODE_PRIVATE);
		
		SharedPreferences.Editor editor=pre.edit();
		String email = edt_mail.getText().toString();
		String pass =edt_pass.getText().toString();
		
		boolean bchk=ck_save.isChecked();
		
		if(!bchk)
		{
			//Xóa dữ liệu lần lưu trước
			editor.clear();
		}
		else
		{
			//lưu vào editor
			editor.putString("email", email);
			editor.putString("pass", pass);
			editor.putBoolean("checked", bchk);
		}
		
		//Luu xuong file
		editor.commit();
	}
	
	/**
	 * hàm đọc trạng thái đã lưu trước đó
	 */
	
	public void restoringPreferences()
	{
		SharedPreferences preFrences=getSharedPreferences(prefname,MODE_PRIVATE);
		
		boolean bchk = preFrences.getBoolean("checked", false);
		if(bchk)
		{
			
			String email = preFrences.getString("email", "");
			String pass = preFrences.getString("pass", "");
			edt_mail.setText(email);
			edt_pass.setText(pass);
		}
		ck_save.setChecked(bchk);
	}
	
	public void createDialog(String message)
	{
		AlertDialog.Builder alertDialog =new AlertDialog.Builder(MainActivity.this);
		//AlertDialog.Builder alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
		 
		alertDialog.setTitle("Alert");
		alertDialog.setMessage(message);
		alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		alertDialog.create().show();
		
	}
}
