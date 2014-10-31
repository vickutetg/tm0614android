package com.NguyenTruong.mstudent;

import java.io.Serializable;

public class Student implements Serializable{
	
	private String name;
	private float toan;
	private float ly;
	private float hoa;
	private float tb;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getToan() {
		return toan;
	}
	public void setToan(float toan) {
		this.toan = toan;
	}
	public float getLy() {
		return ly;
	}
	public void setLy(float ly) {
		this.ly = ly;
	}
	public float getHoa() {
		return hoa;
	}
	public void setHoa(float hoa) {
		this.hoa = hoa;
	}
	public float getTb() {
		return tb;
	}
	public void setTb(float toan, float ly, float hoa) {
		this.tb = (toan + ly + hoa) / 3;
	}
}
