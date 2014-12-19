package com.hoangphan.check12a_student.model;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class StudentDao {
	
	StudentProvider provider;
	
	public StudentDao(Context baseContext) {
		provider = new StudentProvider();
		provider.context = baseContext;
		provider.onCreate();
	}

	public ArrayList<Student> findAll(){
		Cursor c = provider.query(Uri.parse("content://amsterdam/students/"), null, null, null, "name ASC");
		ArrayList<Student> list = new ArrayList<>();
		
		c.moveToFirst();
		while (!c.isAfterLast()) {
			int id = c.getInt(c.getColumnIndex("_id"));
			String name = c.getString(c.getColumnIndex(StudentProvider.StudentHelper.KEY_NAME));
			int math = c.getInt(c.getColumnIndex("math"));
			int physic = c.getInt(c.getColumnIndex("physic"));
			int chemistry = c.getInt(c.getColumnIndex("chemistry"));

			Student s = new Student(id, name, math, physic, chemistry);
			list.add(s);
			c.moveToNext();
		}
		
		return list;
	}
	
	public Student getById(int studentID){
		Cursor c = provider.query(Uri.parse("content://amsterdam/students/"+studentID), null, null, null, "name ASC");
		
		int id = c.getInt(c.getColumnIndex("_id"));
		String name = c.getString(c.getColumnIndex(StudentProvider.StudentHelper.KEY_NAME));
		int math = c.getInt(c.getColumnIndex("math"));
		int physic = c.getInt(c.getColumnIndex("physic"));
		int chemistry = c.getInt(c.getColumnIndex("chemistry"));
		
		return new Student(id, name, math, physic, chemistry);
	}
	
	public long insert(Student student){
		Uri uri = provider.insert(Uri.parse("content://amsterdam/students"), student.convertToBundle());
		int studentID = Integer.parseInt(uri.getPathSegments().get(1));
		return studentID;
	}
	
	public int update(Student student){
		
		return 0;
	}
	
	public int delete(int studentID){
		
		return 0;
	}

}
