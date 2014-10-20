package com.namnguyen.christmas_color;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	final int RED = 1;
	final int GREEN = 2;
	final int YELLOW = 3;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void fillRed(View view) {
    	Context context = getApplicationContext();
    	CharSequence text = "Button Red pressed";
    	int duration = Toast.LENGTH_SHORT;
    	
    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    	
    	changeColorTxtView(RED);
    }	

    public void fillGreen(View view) {
    	Context context = getApplicationContext();
    	CharSequence text = "Button Green pressed";
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    	changeColorTxtView(GREEN);
    }	

    public void fillYellow(View view) {
    	Context context = getApplicationContext();
    	CharSequence text = "Button Yellow pressed";
    	int duration = Toast.LENGTH_SHORT;
    	
    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    	changeColorTxtView(YELLOW);
    }	
    
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        
        TextView t = (TextView)findViewById(R.id.txtView);
        
        switch(view.getId()) {
            case R.id.rbtRed:
                if (checked)
                	t.setBackgroundResource(R.color.red);
                	rbtnToast(RED);
                break;
            case R.id.rbtGreen:
                if (checked)
                	t.setBackgroundResource(R.color.green);
                	rbtnToast(GREEN);
                break;
            case R.id.rbtYellow:
                if (checked)
                	t.setBackgroundResource(R.color.yellow);
                	rbtnToast(YELLOW);
                break;
        }
    }
    
    public void changeColorTxtView(int i) {
    	
    	TextView t = (TextView)findViewById(R.id.txtView);
    	
    	switch(i) {
	    	case 1:
	    		t.setBackgroundResource(R.color.red);
	    	break;
	    	case 2:
	    		t.setBackgroundResource(R.color.green);
			break;
	    	case 3:
	    		t.setBackgroundResource(R.color.yellow);
			break;
    	}
    }
    
    public void rbtnToast(int i) {
    	Context context = getApplicationContext();
    	
    	CharSequence text = "";
    	
    	switch(i) {
	    	case 1:
	    		text = "Radio Button Red Choosen";
	    	break;
	    	case 2:
	    		text = "Radio Button Green Choosen";
    		break;
	    	case 3:
	    		text = "Radio Button Yellow Choosen";
    		break;
    	}
    	
    	int duration = Toast.LENGTH_SHORT;
    	
    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    }
}
