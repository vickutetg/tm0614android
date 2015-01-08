package com.tuannd.lap04_login;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class CustomDialogClass extends Dialog implements
		android.view.View.OnClickListener {

	public Activity context;
	public Dialog dialog;
	public Button ok;

	public CustomDialogClass(Activity context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.custom_dialog_class);
		this.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		ok = (Button) findViewById(R.id.id_alertButton);
		ok.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_alertButton:
			dismiss();
			break;
		default:
			break;
		}
		dismiss();
	}
}
