package com.hoangphan.fourpic;

import com.hoangphan.fourpic.db.DBAdapterTest;
import com.hoangphan.fourpic.db.Problem;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
//	static DBAdapter dbAdapter;
	static DBAdapterTest dbAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		dbAdapter = new DBAdapter(this);
		dbAdapter = new DBAdapterTest(this);
		this.selectProblem();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * Select problem.
	 */
	private void selectProblem() {
		
		Problem problem = this.dbAdapter.getProblem(1);
		
		// make hint area
		TableLayout hintArea = (TableLayout)findViewById(R.id.HintArea);
		TableRow hintRow1 = new TableRow(this);
		TableRow hintRow2 = new TableRow(this);
		hintArea.addView(hintRow1);
		hintArea.addView(hintRow2);
		// set hint
		String[] hint = problem.getHintArray();
		
		for (int i = 0; i < hint.length; i++) {
			Button btn = new Button(this);
			new HintAlphabet(btn, hint[i]);
			
			if ( i < 5) {
				hintRow1.addView(btn);
			} else {
				hintRow2.addView(btn);
			}
		}
		
		// make solve area 
		TableRow solveArea = (TableRow)findViewById(R.id.SolveArea);
		
		String[] solve = problem.getSolveArray();
		
		for (int i = 0; i < solve.length; i++) {
			SolveTextView solveText = new SolveTextView(this);
			
			solveText.setAnswer(solve[i]);
			solveArea.addView(solveText);
		}
		
	}
	/** Hint alphabet button 
	 *  */
	class HintAlphabet implements OnClickListener {
		String s;
		Button btn;
		
		public HintAlphabet(Button btn , String s) {
			this.s = s;
			btn.setText(s);
			btn.setOnClickListener(this);
			this.btn = btn;
		}
		
		public void onClick(View v) {
			TableRow solveArea = (TableRow)findViewById(R.id.SolveArea);
			
			boolean isSolved = false;
			int cntSolved = 0;
			
			int cnt = solveArea.getChildCount();
			for ( int i = 0 ; i < cnt; i++) {
				SolveTextView solve = (SolveTextView)solveArea.getChildAt(i);
				if (!isSolved && "".equals(solve.getText())){
					solve.setSolveText(this.s, this.btn);
					isSolved = true;
				}
				
				if ( !"".equals(solve.getText())) {
					cntSolved++;
				}
			}
			
			// Disabled clicked hint button 
			this.btn.setEnabled(false);
			
			if ( cnt == cntSolved) {
				checkCorrect();
			}
			
		}
		
		public void checkCorrect() {
			int message = R.string.correct_Message;
			
			TableRow solveArea = (TableRow)findViewById(R.id.SolveArea);
			int cnt = solveArea.getChildCount();
			for ( int i = 0 ; i < cnt; i++) {
				SolveTextView solve = (SolveTextView)solveArea.getChildAt(i);
				message = message & solve.isCorrect();
			}
			
			if (R.string.correct_Message != message ) {
				message = R.string.inCorrect_Message;
			}
			
			showDialog(R.string.correct_Title, message);
		}
		
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		void showDialog(int title, int message) {
			DialogFragment fragment = MyDialogFragment.newInstance(title, message);
			fragment.show(getFragmentManager(), "dialog");
		}
	}
}
