package com.hoangphan.tutor1402_fancysms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//khi ma co mot event goi receiver
		int price = intent.getIntExtra("price", 0);
		Toast.makeText(context, "Tao nghe duoc gia cua chung may la:"+price, 
				Toast.LENGTH_LONG).show();
	}
}
