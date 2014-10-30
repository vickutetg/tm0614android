package com.vanlinh;

import java.io.Serializable;

public class Student implements Serializable{
	
	private String ten;
	private int toan;
	private int ly;
	private int hoa;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String ten, int toan, int ly, int hoa) {
		super();
		this.ten = ten;
		this.toan = toan;
		this.ly = ly;
		this.hoa = hoa;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public int getToan() {
		return toan;
	}
	public void setToan(int toan) {
		this.toan = toan;
	}
	public int getLy() {
		return ly;
	}
	public void setLy(int ly) {
		this.ly = ly;
	}
	
	public int getHoa() {
		return hoa;
	}
	public void setHoa(int hoa) {
		this.hoa = hoa;
	}
	
	public int diemTB(){
		return ( getToan() + getLy() + getHoa() ) / 3;
	}
	
	@Override
	public String toString() {
		return getTen() + "-" + this.diemTB();
	}
	
}
