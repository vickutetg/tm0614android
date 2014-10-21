package com.tuannd.day3_Colorist;

import java.util.Random;

import com.example.day2_randomcolor.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewColor;
	private Button buttonColor;
	private final int setRed = 0, setBlue = 1, setYellow = 2;
	private int textColor = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textViewColor = (TextView) findViewById(R.id.textViewColor);
		buttonColor = (Button) findViewById(R.id.btnColor);

		buttonColor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Random random = new Random();
				int temp = random.nextInt(3);

				switch (temp) {
				case setRed:
					textViewColor.setBackgroundColor(Color.parseColor("red"));
					textViewColor.setText(" Now Background Color is RED");
					break;
				case setBlue:
					textViewColor.setBackgroundColor(Color.parseColor("blue"));
					textViewColor.setText(" Now Background Color is BLUE");
					break;
				case setYellow:
					textViewColor.setBackgroundColor(Color.parseColor("yellow"));
					textViewColor.setText(" Now Background Color is YELLOW");
					break;
				default:
					break;
				}

				if (textColor != temp) {
					switch (textColor) {
					case setRed:
						textViewColor.setTextColor(Color.parseColor("red"));						
						textColor = temp;
						break;
					case setBlue:
						textViewColor.setTextColor(Color.parseColor("blue"));
						textColor = temp;
						break;
					case setYellow:
						textViewColor.setTextColor(Color.parseColor("yellow"));
						textColor = temp;
						break;
					default:
						break;
					}
				}
			}
		});
	}
}
