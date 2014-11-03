package com.vanlinh;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button btnLogin, btnPassword;
	EditText etSearch;
	Button btnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnPassword = (Button) findViewById(R.id.button2);
		
		btnPassword.setText((Html.fromHtml("<font size='20' color='green'>Forgot</font><br/><font size='10' color='blue'>password</font>")));
		
		btnLogin.setOnClickListener(new ClickListener());
	}

	class ClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
//			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//			builder.setTitle("Alert")
//			.setCancelable(false)
//			.setMessage("Your password is wrong.\nPlease try again")
//			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//				
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//					dialog.cancel();
//				}
//			});
//			builder.show();
			
//			final Dialog dialog = new Dialog(MainActivity.this);
//			
//			dialog.setContentView(R.layout.custom_dialog);
//			dialog.setTitle("Alert");
//			dialog.setCancelable(false);
//			
////			TextView title = (TextView) dialog.findViewById(R.id.title);
//			TextView message = (TextView) dialog.findViewById(R.id.textView1);
//			message.setText("Custom dialog Android example.");
//			Button button1 = (Button) dialog.findViewById(R.id.button1);
//			
//			dialog.show();
//			
//			button1.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					dialog.dismiss();
//				}
//			});
			
			final Dialog dialog = new Dialog(MainActivity.this, android.R.style.Theme_Translucent);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

			dialog.setCancelable(true);
			dialog.setContentView(R.layout.dialog);

			etSearch = (EditText) dialog.findViewById(R.id.etsearch);
			btnCancel = (Button) dialog.findViewById(R.id.btncancel);

			btnCancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});

			dialog.show();
		}
	}

}
