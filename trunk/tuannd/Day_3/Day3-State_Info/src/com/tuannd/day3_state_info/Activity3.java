package com.tuannd.day3_state_info;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity3 extends BaseActivity {

	private String strAc1 = "Activity1(Stop)";
	private Button btnAc1_left, btnAc2_left, btnAc3_left, btnAc1_right,
			btnAc2_right, btnAc3_right;
	private TextView viewState;
	static Activity3 activity3;

	// private String viewLast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity3);
		activity3 = this;
		keyState = keyS3;
		init();
		// initViewState();
		viewLast = "";
		viewLast += "Activity 3 Create\n";
		// viewState.setText(view);
	}

	public void init() {
		btnAc1_left = (Button) findViewById(R.id.id_btnActivity1_left);
		btnAc2_left = (Button) findViewById(R.id.id_btnActivity2_left);
		btnAc3_left = (Button) findViewById(R.id.id_btnActivity3_left);

		btnAc1_right = (Button) findViewById(R.id.id_btnActivity1_Right);
		btnAc2_right = (Button) findViewById(R.id.id_btnActivity2_Right);
		btnAc3_right = (Button) findViewById(R.id.id_btnActivity3_Right);

		viewState = (TextView) findViewById(R.id.id_textState);
	}

	public static Activity3 getInstance() {
		return activity3;
	}

	public void Click_Activity1_left(View v) {
		Intent intent = new Intent(Activity3.this, Activity1.class);
		startActivity(intent);
	}

	public void Click_Activity1_Right(View v) {
		Activity1.getInstance().finish();
	}

	public void Click_Activity2_left(View v) {
		Intent intent = new Intent(Activity3.this, Activity2.class);
		startActivity(intent);
	}

	public void Click_Activity2_Right(View v) {
		Activity2.getInstance().finish();
	}

	public void Click_Activity3_left(View v) {
		onStop();
	}

	public void Click_Activity3_Right(View v) {
		finish();
	}

	@Override
	protected void onStart() {
		super.onStart();
		initViewState();
		viewLast += "Activity 3 Start\n";
	}

	@Override
	protected void onResume() {
		super.onResume();
		viewLast += "Activity 3 Resume\n";
		viewState.setText(viewFisrt + viewLast);
	}
}
