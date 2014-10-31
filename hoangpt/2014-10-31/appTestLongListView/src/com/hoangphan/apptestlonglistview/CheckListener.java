package com.hoangphan.apptestlonglistview;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class CheckListener implements OnClickListener {

	int position;
	
	public CheckListener(int position) {
		this.position = position;
	}

	@Override
	public void onClick(View v) {
		CheckBox cb = (CheckBox) v;
		if(cb.isChecked()){
			WorkApplication.setCheck(position);
		}
	}
}
