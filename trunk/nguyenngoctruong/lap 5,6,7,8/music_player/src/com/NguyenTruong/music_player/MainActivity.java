package com.NguyenTruong.music_player;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	int check;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button btnPlay = (Button) findViewById(R.id.btnPlay);
		check = R.drawable.btn_play;
		btnPlay.setBackgroundResource(check);

		btnPlay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (check == R.drawable.btn_play) {
					btnPlay.setBackgroundResource(R.drawable.btn_pause);
					check = R.drawable.btn_pause;
				}else {
					btnPlay.setBackgroundResource(R.drawable.btn_play);
					check = R.drawable.btn_play;
				}

//				if (check == R.drawable.btn_pause) {
//					btnPlay.setBackgroundResource(R.drawable.btn_play);
//					check = R.drawable.btn_play;
//				}

			}
		});

	}
}
