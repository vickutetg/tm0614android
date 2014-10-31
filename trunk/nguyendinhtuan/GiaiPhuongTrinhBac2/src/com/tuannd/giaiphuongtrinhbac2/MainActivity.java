package com.tuannd.giaiphuongtrinhbac2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView txtResult;
	private EditText edtA, edtB, edtC;
	private double x1 = 0, x2 = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	void init() {
		edtA = (EditText) findViewById(R.id.id_edtA);
		edtB = (EditText) findViewById(R.id.id_edtB);
		edtC = (EditText) findViewById(R.id.id_edtC);
		txtResult = (TextView) findViewById(R.id.id_txtResult);

	}

	public void clickSubmit(View v) {
		boolean result = false;
		String a = edtA.getText().toString();
		String b = edtB.getText().toString();
		String c = edtC.getText().toString();
		if (!a.equals("") && !b.equals("") && !c.equals("")) {
			result = mathSolver(Double.parseDouble(a), Double.parseDouble(b),
					Double.parseDouble(c));
		}

		if (result) {
			txtResult.setText("Nghiem cua Phuong Trinh bac hai:\n   " + a
					+ "x2 + " + b + "x + " + c + " la:\nx1= " + x1 + "\nx2= "
					+ x2);
		}
	}

	public boolean mathSolver(double a, double b, double c) {
		double D = b * b - 4 * a * c;

		if (D == 0) {
			x1 = -b / (2 * a);
			x2 = x1;
			return true;
		} else if (D > 0) {
			x1 = (-b - Math.sqrt(D)) / (2 * a);
			x2 = (-b + Math.sqrt(D)) / (2 * a);
			return true;
		} else {
			return false;
		}
	}
}
