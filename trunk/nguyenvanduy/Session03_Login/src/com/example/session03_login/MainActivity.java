package com.example.session03_login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	Button btn_login, btn_forgot;
	EditText txt_email, txt_pass;
	
	String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_login = (Button)findViewById(R.id.btn_login);
		btn_forgot = (Button)findViewById(R.id.btn_forgot);
		txt_email = (EditText)findViewById(R.id.txt_mail);
		txt_pass = (EditText)findViewById(R.id.txt_pass);
		
		btn_login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String mail = txt_email.getText().toString();
				String pass = txt_pass.getText().toString();
				
				Boolean b = mail.matches(emailreg);
				Boolean flag = true;
				
				if(!b) {
					createDialog("Email không đúng định dạng ");
					flag = false;
					
				} else {
					if(pass.length() > 255 || pass.length() < 6){
						createDialog("Password phải có độ dài từ 6 - 255 ký tự ");
						flag = false;
					}
				}
				
				if(flag){
					createDialog("OK");
				}
				
				
			}
		});
		
		
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
