package com.tuannd.lap04_hcmquotes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.tuannd.lap04_hcmquotes.fragment.HCM_Fragment;
import com.tuannd.lap04_hcmquotes.fragment.TT_Fragment;
import com.tuannd.lap04_hcmquotes.fragment.VNG_Fragment;

public class StateActivity extends FragmentActivity {

	Fragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_state);
		Bundle bundle = getIntent().getExtras();
		int id = 0;
		if (bundle != null) {
			id = bundle.getInt("_ID");
		}
		Log.d("LOG", id + "");
		switch (id) {
		case 0:
			fragment = new HCM_Fragment();
			break;
		case 1:
			fragment = new VNG_Fragment();
			break;
		case 2:
			fragment = new TT_Fragment();
			break;
		default:
			fragment = new HCM_Fragment();
			break;
		}
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.id_state_activity, fragment);
		transaction.commit();
	}
}
