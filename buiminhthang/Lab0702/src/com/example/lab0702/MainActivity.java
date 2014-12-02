package com.example.lab0702;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextView tv1 = (TextView) findViewById(R.id.tv1);
		Typeface custom_font = Typeface.createFromAsset(getAssets(),
				"fonts/GothaProMed.otf");
		tv1.setTypeface(custom_font);
    }
}
