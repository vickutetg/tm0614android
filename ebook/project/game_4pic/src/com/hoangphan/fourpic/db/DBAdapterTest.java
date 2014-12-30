package com.hoangphan.fourpic.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;


public class DBAdapterTest {

	protected final Context context;
	
	private List<Problem> problemStockList;
	
	
	public DBAdapterTest(Context context) {
		this.context = context;
		this.problemStockList = new ArrayList<Problem>(3);
		this.problemStockList.add( new Problem("PILOT","PILOTABC"));
		this.problemStockList.add( new Problem("HOME", "HOMEABC"));
		this.problemStockList.add( new Problem("SURFER","SURFERABC"));
	}
	
	public DBAdapter openWritable() {
		return null;
	}
	
	public DBAdapter openReadable() {
		return null;
	}
	
	public void close(){
	}
	
	public Problem getProblem(int i) {
		Problem problem = this.problemStockList.get(i);
		return problem;
	}
}
