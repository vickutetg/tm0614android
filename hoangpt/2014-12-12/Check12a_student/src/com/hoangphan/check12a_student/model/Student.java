package com.hoangphan.check12a_student.model;

import java.util.ArrayList;

import android.content.ContentValues;


public class Student {
	
	int id = 0;
	String name;
	int math;
	int physic;
	int chemistry;
	
	public Student(int id, String name, int math, int physic, int chemistry) {
		super();
		this.id = id;
		this.name = name;
		this.math = math;
		this.physic = physic;
		this.chemistry = chemistry;
	}
	
	public ContentValues convertToBundle(){
		ContentValues v = new ContentValues();

		v.put("name", name);
		v.put("math", math);
		v.put("physic", physic);
		v.put("chemistry", chemistry);
		
		return v;
	}

	@Override
	public String toString() {
		double average = (math+physic+chemistry)/3;
		return name+": "+average;
	}
	
	

	
}
