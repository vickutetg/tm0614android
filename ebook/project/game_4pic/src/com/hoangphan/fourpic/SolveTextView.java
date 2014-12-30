package com.hoangphan.fourpic;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SolveTextView extends TextView implements OnClickListener {
	private String answer;
	private Button hintBtn;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public SolveTextView(Context context) {
		super(context);
		this.setOnClickListener(this);
		this.setTextAppearance(getContext(), R.style.SolveText_normal);
	}
	
	public int isCorrect() {
		if ( this.getAnswer().equals(this.getText())) {
			this.setTextAppearance(getContext(), R.style.SolveText_correct);
			this.setBackgroundResource(R.color.correctBackgroundColor);
			return R.string.correct_Message;
		} else {
			this.setTextAppearance(getContext(), R.style.SolveText_incorrect);
			this.setBackgroundResource(R.color.inCorrectBackgroundColor);
			return R.string.inCorrect_Message;
		}
	}
	
	public void setSolveText(String s, Button btn) {
		this.setText(s);
		this.hintBtn = btn;
	}
	
	// TODO addTextChangedListener
	@Override
	public void onClick(View v) {
		this.setText("");
		this.setBackgroundResource(R.color.solveBackgroundColor);
		// 
		this.hintBtn.setEnabled(true);
	}
}
