package com.tuannd.lap04_hcmquotes;

import com.tuannd.lap04_hcmquotes.fragment.HCM_Fragment;
import com.tuannd.lap04_hcmquotes.fragment.TT_Fragment;
import com.tuannd.lap04_hcmquotes.fragment.VNG_Fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

	ListView listView;
	Fragment fragment;
	FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.id_listView);
		listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.data_items)));
		Configuration config = getResources().getConfiguration();
		if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
			initList_Portraint();
		} else if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			initList_Landscape();
		}
	}

	private void initList_Portraint() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// Log.d("LOG", arg2 + "");
				Intent intent = new Intent(MainActivity.this,
						StateActivity.class);
				switch (arg2) {
				case 0:
					intent.putExtra("_ID", 0);
					startActivity(intent);
					break;
				case 1:
					intent.putExtra("_ID", 1);
					startActivity(intent);
					break;
				case 2:
					intent.putExtra("_ID", 2);
					startActivity(intent);
					break;
				default:
					break;
				}
			}
		});
	}

	private void initList_Landscape() {

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				switch (arg2) {
				case 0:
					fragment = new HCM_Fragment();
					transaction = getSupportFragmentManager()
							.beginTransaction();
					transaction.replace(R.id.id_layoutLandscape, fragment);
					// transaction.addToBackStack(null);
					transaction.commit();
					break;
				case 1:
					fragment = new VNG_Fragment();
					transaction = getSupportFragmentManager()
							.beginTransaction();
					transaction.replace(R.id.id_layoutLandscape, fragment);
					// transaction.addToBackStack(null);
					transaction.commit();
					break;
				case 2:
					fragment = new TT_Fragment();
					transaction = getSupportFragmentManager()
							.beginTransaction();
					transaction.replace(R.id.id_layoutLandscape, fragment);
					// transaction.addToBackStack(null);
					transaction.commit();
					break;
				default:
					break;
				}
			}
		});
	}
}
