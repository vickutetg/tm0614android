package com.hoangphan.tutor1402_fancysms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//service call receiver
		Bundle bundle = intent.getExtras();
		
		//lấy message từ intent (message ->pdus)
		Object messages[] = (Object[]) bundle.get("pdus"); //nhieu part
		SmsMessage smsMessage[] = new SmsMessage[messages.length];		
		
		//ghep cac part lai voi nhau
		String msg = ""; 
		int smsPieces = messages.length;

		for (int n = 0; n < smsPieces; n++) {
			smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
			// grab all pieces of the intercepted sms
			msg 	+= "\n" + (n + 1) + " -of- " + smsPieces + "\n" 
						+ "Sender:\t" 	+ smsMessage[n].getOriginatingAddress() + "\n"
						+ "Body:  \n " + smsMessage[n].getMessageBody();
		}
		
		Toast toast = Toast.makeText(context, "FANCY >>> Received SMS: "
				+ smsMessage[0].getMessageBody(), Toast.LENGTH_LONG);
		toast.show();
		
		Log.d("sms", msg);
		this.abortBroadcast();
		
		//tu receiver goi MainActivity cua app
        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mainActivityIntent);

        //send a broadcast intent to update the SMS received in the activity
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("SMS_RECEIVED_ACTION");
        broadcastIntent.putExtra("sms", msg);
        context.sendBroadcast(broadcastIntent);
	}
}
