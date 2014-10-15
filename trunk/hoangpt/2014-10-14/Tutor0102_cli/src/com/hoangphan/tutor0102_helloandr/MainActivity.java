package com.hoangphan.tutor0102_helloandr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);

        //layout
        LinearLayout layout = new LinearLayout(this);

        TextView text = new TextView(this);
        text.setText("I am sexy android");
        layout.addView(text);

        setContentView(layout);	
    }
}
