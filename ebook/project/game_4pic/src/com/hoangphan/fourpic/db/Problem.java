package com.hoangphan.fourpic.db;

public class Problem {
	public boolean solved = false;
	public String solve;
	public String hint;

	Problem( String solve, String hint) {
		this.solve = solve;
		this.hint = hint;
	}

	public String getSolve() {
		return solve;
	}
	
	public String[] getSolveArray() {
		String[] strArray = new String[this.solve.length()];
		
		for(int i = 0; i <this.solve.length(); i++) {
			strArray[i] = ""+this.solve.charAt(i);
		}
		
		return strArray;
	}

	public void setSolve(String solve) {
		this.solve = solve;
	}

	public String getHint() {
		return hint;
	}
	
	public String[] getHintArray() {
		String[] charHint = new String[this.hint.length()];
		
		for(int i = 0; i <this.hint.length(); i++) {
			charHint[i] = ""+this.hint.charAt(i);
		}
		
		return charHint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}


	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}
	
	/**
	 * 
	 * @return true:correct, false: incorrect
	 */
	public boolean isAnswer(String input) {
		if (input == null || "".equals(input)) return false;
		
		if (this.getSolve().equals(input)) {
			this.setSolved(true);
			return true;
		}
		
		return false;
	}

}