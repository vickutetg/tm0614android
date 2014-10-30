package com.tuannd.day3_state_info;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class BaseActivity extends Activity {

	protected String keyState;
	protected String viewFisrt;
	protected String viewMid;
	protected String viewLast;
	protected final String keyS1 = "ACTIVITY_1_STATE";
	protected final String keyS2 = "ACTIVITY_2_STATE";
	protected final String keyS3 = "ACTIVITY_3_STATE";
	protected final String defaultState = "NotRun";

	protected void initViewState() {
		viewFisrt = "";
		viewFisrt += "Activity 1: " + checkState(keyS1) + "\n";
		viewFisrt += "Activity 2: " + checkState(keyS2) + "\n";
		viewFisrt += "Activity 3: " + checkState(keyS3) + "\n";
	}

	protected String checkState(String keyState) {
		SharedPreferences preferences = getSharedPreferences("ActivityInfo",
				MODE_PRIVATE);
		if (preferences.contains(keyState)) {
			return preferences.getString(keyState, defaultState);
		}
		return defaultState;
	}

	@Override
	protected void onStart() {
		super.onStart();
		SharedPreferences preferences = getSharedPreferences("ActivityInfo",
				MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(keyState, "Start");
		editor.commit();

		Log.d("LOG", "Start");
	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences preferences = getSharedPreferences("ActivityInfo",
				MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(keyState, "Pause");
		editor.commit();
		viewLast = "";

		Log.d("LOG", "Pause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		super.onPause();
		SharedPreferences preferences = getSharedPreferences("ActivityInfo",
				MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(keyState, "Stop");
		editor.commit();
		viewLast = "";

		Log.d("LOG", "Stop");
	}
}
