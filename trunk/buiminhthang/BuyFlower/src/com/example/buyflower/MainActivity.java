/**
 * Bui Minh Thang
 * email: buiminhthangg@gmail.com
 */
package com.example.buyflower;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	String after = null;
	int $i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText edFlower = (EditText) findViewById(R.id.edFlower);
		Button btBuy = (Button) findViewById(R.id.btBuy);
		Button btCheckout = (Button) findViewById(R.id.btCheckout);
		final TextView tvFlower = (TextView) findViewById(R.id.tvFlower);

		/**
		 * Set onclick buy button
		 */
		btBuy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String before = edFlower.getText().toString();
				after = before + "\n";
				String b = tvFlower.getText().toString();
				tvFlower.setText(after + b);

				Toast.makeText(getApplicationContext(),
						after + " add to order.", Toast.LENGTH_SHORT).show();

				$i = $i + 1000;
			}
		});

		/**
		 * Set onclick checkout button
		 */
		btCheckout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// get current time
				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"HH:mm:ss a dd:MMMM:yyyy");
				String strDate = sdf.format(c.getTime());

				// set alertDialog
				AlertDialog checkOut = new AlertDialog.Builder(
						MainActivity.this).create();
				checkOut.setTitle("Checkout..");
				checkOut.setMessage("Thank you for your order of my flower is "
						+ $i + "$" + "\n" + "Order create at: " + "\n"
						+ strDate);
				checkOut.setButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getApplicationContext(), "Thank you!",
								Toast.LENGTH_SHORT).show();
					}
				});
				checkOut.show();
			}
		});

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
}
