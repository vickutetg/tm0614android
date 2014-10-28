/**
 * http://iziroi.9xkun.com
 * 
 * LICENSE
 *
 * This source file is belong to iziroi.9xkun.com.
 * Please come to this site and get more source code.
 * Can send email to me at: phantichhoang@gmail.com
 * 
 * @copyright Copyright (c) 20013-2014 iziroi
 * @author    hoangpt
 * @version   $Id$
 * @since     
 */

package com.hoangphan.tutor0201_helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView helloTxt;
	EditText nameEdit;

	public class NameListener implements View.OnKeyListener {

		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if (KeyEvent.ACTION_DOWN == event.getAction()
					&& KeyEvent.KEYCODE_ENTER == keyCode) {
				String name = nameEdit.getText().toString();
				nameEdit.setText("");// reset
				helloTxt.setText("Welcome to Android world, " + name + ".");
				return true;
			} else {
				return false;
			}
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);

		// create textview hello world, like swing, first is layout
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);

		//add textview to layout
		helloTxt = new TextView(this);
		helloTxt.setText("Welcome to Android world");

		//add edittext to layout
		nameEdit = new EditText(this);
		nameEdit.setHint("Enter your name");
		nameEdit.setLines(1);

		layout.addView(helloTxt);
		layout.addView(nameEdit);

		nameEdit.setOnKeyListener(new NameListener());
		setContentView(layout);
	}
}
