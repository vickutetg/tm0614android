package com.namnguyen.namnh_student_management;

import java.io.Serializable;

public class Student implements Serializable {

	private String 	name;
	private int 	math;
	private int		physic;
	private int 	chemistry;
	
	public Student(String name, int math, int physic, int chemistry) {
		super();
		this.name = name;
		this.math = math;
		this.physic = physic;
		this.chemistry = chemistry;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getPhysic() {
		return physic;
	}
	public void setPhysic(int physic) {
		this.physic = physic;
	}
	public int getChemistry() {
		return chemistry;
	}
	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}
	
	public int getTotal() {
		return (math+physic+chemistry)/3;
	}
	
	@Override
	public String toString() {
		return String.format("%s\t\t\t\t\t\t\t\t\t\t%d", name, getTotal());
		
		//return name + "\t\t" + total;
	}
}
