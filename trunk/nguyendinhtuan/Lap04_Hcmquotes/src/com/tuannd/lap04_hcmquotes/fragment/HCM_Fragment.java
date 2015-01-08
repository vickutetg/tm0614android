package com.tuannd.lap04_hcmquotes.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HCM_Fragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(
				com.tuannd.lap04_hcmquotes.R.layout.fragment_hcm, container,
				false);
		return view;
	}
}
