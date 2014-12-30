package com.hoangphan.tutor1501_sms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String phone;
	private String message;
	private SmsSentListener sendRC;
	private SmsReceiveListener receiveRC;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//ui init
		phone = ((EditText)findViewById(R.id.edtPhone)).getText().toString();
		message = ((EditText)findViewById(R.id.edtSMS)).getText().toString();
	}
	
	public void defaultSMS(View v){
		//call activity sms app
		Intent i = new 
				Intent(android.content.Intent.ACTION_VIEW);
		i.putExtra("address", phone);
		i.putExtra("sms_body", message);
		i.setType("vnd.android-dir/mms-sms");
		//i.setAction("sms://5556?body=Hello my friend");
		startActivity(i);		
	}
	
	public void ownSMS(View v){
		//call SMSManager --> send
		//sms manager --> send
		SmsManager sms = SmsManager.getDefault();
		
		//kiem tra sms da duoc gui di chua PendingIntent Send
		Intent iSent = new Intent("ALREADY_SENT");
		IntentFilter ifSend = new IntentFilter("ALREADY_SENT");
		sendRC = new SmsSentListener();
		registerReceiver(sendRC, ifSend);
		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, iSent, PendingIntent.FLAG_UPDATE_CURRENT);		
		
		//kiem tra xem nguoi nhan da nhan duoc chua PendingIntent Receive
		Intent iReceive = new Intent("ALREADY_RECEIVE");
		IntentFilter ifReceive = new IntentFilter("ALREADY_RECEIVE");
		receiveRC = new SmsReceiveListener();
		registerReceiver(receiveRC, ifReceive);
		PendingIntent receivePI = PendingIntent.getBroadcast(this, 0, iReceive, PendingIntent.FLAG_UPDATE_CURRENT);		
		
		sms.sendTextMessage("34032548", 
				phone, message, 
				sentPI, receivePI); //khong can kiem tra
	}
	
	
	public class SmsSentListener extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			switch (getResultCode()) {
		    case Activity.RESULT_OK:
		    		Toast.makeText(context, "sent to carrier", Toast.LENGTH_SHORT).show();
		        
		    		//goi them app gi do
		    		
		    		//thu tien gi do, goi wallet ra thu tien
		    		break;
		    default:
		    		Toast.makeText(context, "something error", Toast.LENGTH_SHORT).show();
		    		break;
			}    		
		}
	}
	
	public class SmsReceiveListener extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			switch (getResultCode()) {
		    case Activity.RESULT_OK:
		    		Toast.makeText(context, "sent to receiver", Toast.LENGTH_SHORT).show();
		        break;
		    default:
		    		Toast.makeText(context, "something error", Toast.LENGTH_SHORT).show();
		    		break;
			}    		
		}
	}
	
}
