package com.namnguyen.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import com.namnguyen.objects.Student;

public class ListStudent {
	
	private ArrayList<Student> studentList = new ArrayList<Student>();
	
	///////////////////////////////////////////////////
	// Using Singleton pattern
	///////////////////////////////////////////////////
	private static ListStudent listStudent = new ListStudent();
	
	///////////////////////////////////////////////////
	// Default constructor
	///////////////////////////////////////////////////	
	private ListStudent() {
	}
	
	public static ListStudent getListStudentInstance() {
		return listStudent;
	}
	
	///////////////////////////////////////////////////
	// Method to add student into an ArrayList
	// false -> Error Occurred, NOT insert
	// true -> OK, new student inserted
	///////////////////////////////////////////////////	
	public boolean addStudent(String name, int id, String className, float markBasicJava, float markAdvJava, float markCSharp, 
			float markAdvCSharp, float markRDBMS, float markSQL2008) {
		
		// Check Duplicate ID, valid mark input
		if (!checkDuplicateID(id) && checkMark(markBasicJava) && checkMark(markAdvJava) && checkMark(markCSharp) 
				&& checkMark(markAdvCSharp) && checkMark(markRDBMS) && checkMark(markSQL2008)) {
			
			float 	avgMark;
			String 	rank;
			
			avgMark = calculateAvgMark(markBasicJava, markAdvJava, markCSharp, markAdvCSharp, markRDBMS, markSQL2008);
			rank 	= calculateRank(avgMark);
			
			Student student = new Student(name, id, className, markBasicJava, markAdvJava, markCSharp, 
					markAdvCSharp, markRDBMS, markSQL2008);
			studentList.add(student);
			
			System.out.format("->%s (id=%d) has average mark of %.2f, rank %s", student.getName(), student.getID(), student.getAvgMark(), rank);
			System.out.println();
			
			return true;
		} else {
			return false;
		}
	}
	
	///////////////////////////////////////////////////
	// Get the size of Student list
	///////////////////////////////////////////////////
	public int getSize() {
		return studentList.size();
	}
	
	///////////////////////////////////////////////////
	// Print out the student list
	///////////////////////////////////////////////////	
	public void printStudentList() {
		
		sortStudentList();
		
		Iterator<Student> itr = studentList.iterator();
		
		System.out.println("Name\t\tid\tclass\t\taverage\t\trank");
		System.out.println("============================================================================");
		
		while(itr.hasNext()) {
			Student student = itr.next();
			
			float avgMark = calculateAvgMark(student.getMarkBasicJava(), student.getMarkAdvJava(), student.getMarkCSharp(), student.getMarkAdvCSharp(), student.getMarkRDBMS(), student.getMarkSQL2008());
			String rank = calculateRank(avgMark);
			
			System.out.format("%s\t\t%d\t%s\t%.2f\t\t%s", student.getName(), student.getID(), student.getClassName(), avgMark, rank);

			System.out.println();
		}
		System.out.println("============================================================================");
		
	}

	///////////////////////////////////////////////////
	// Sort the student list
	///////////////////////////////////////////////////	
	public void sortStudentList() {
		Collections.sort(studentList, new Comparator<Student>() {
			public int compare(Student student1, Student student2) {
				int result = Float.compare(student1.getAvgMark(), student2.getAvgMark());
				if (result == 0)
				  result = Float.compare(student1.getID(), student2.getID());
				return -result;
            }
		});
	}
	
	///////////////////////////////////////////////////
	// Check if duplicated id
	// if false -> OK, allow insert 
	// if true -> error, NOT allow insert
	///////////////////////////////////////////////////
	private boolean checkDuplicateID(int id) {
		
		Iterator<Student> itr = studentList.iterator();
		
		while(itr.hasNext()) {
			Student student = itr.next();
			if(student.getID() == id) {
				return true;
			}
		}
		
		return false;
	}
	
	///////////////////////////////////////////////////
	// Check if mark input
	// mark must be a valid number (0 <= mark <= 10)
	// if false -> error, NOT allow insert 
	// if true -> OK, allow insert
	///////////////////////////////////////////////////	
	private boolean checkMark(float mark) {
		if(mark >= 0 && mark <= 10) {
			return true;
		} else {
			return false;
		}
	}
	
	///////////////////////////////////////////////////
	// Calculate the average mark
	///////////////////////////////////////////////////
	private float calculateAvgMark(float markBasicJava, float markAdvJava, float markCSharp, 
			float markAdvCSharp, float markRDBMS, float markSQL2008) {
		return (markBasicJava + markAdvJava + markCSharp + markAdvCSharp + markRDBMS + markSQL2008) / 6;
	}
	
	///////////////////////////////////////////////////
	// Set the rank base on average mark
	///////////////////////////////////////////////////	
	private String calculateRank(float avgMark) {
		if (avgMark >= 9) {
			return "Excellent";
		} else if (avgMark >= 8) {
			return "Good";
		} else if (avgMark >= 6) {
			return "Fairly Good";
		} else if (avgMark >= 5) {
			return "Fair";
		} else if (avgMark >= 3) {
			return "Poor";
		} else {
			return "False";
		}
	}
}
