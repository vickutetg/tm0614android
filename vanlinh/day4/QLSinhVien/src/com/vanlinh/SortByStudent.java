package com.vanlinh;

import java.util.Comparator;

public class SortByStudent implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		int tb1 = s1.diemTB();
		int tb2 = s2.diemTB();
		
		if( tb1 < tb2){
			return 1;
		}else if( tb1 == tb2){
			return 0;
		}else{
			return -1;
		}
	}

}
