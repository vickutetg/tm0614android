package com.tuannd.quanlysv;

public class Student implements Comparable<Student> {
	private String name;
	private double hoa;
	private double ly;
	private double toan;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHoa() {
		return hoa;
	}

	public void setHoa(double hoa) {
		this.hoa = hoa;
	}

	public double getLy() {
		return ly;
	}

	public void setLy(double ly) {
		this.ly = ly;
	}

	public double getToan() {
		return toan;
	}

	public void setToan(double toan) {
		this.toan = toan;
	}

	public double caculate(double diemHoa, double diemLy, double diemToan) {
		return (diemHoa + diemLy + diemToan) / 3;
	}

	@Override
	public String toString() {
		return name + "  " + caculate(hoa, ly, toan);
	}

	@Override
	public int compareTo(Student another) {

		if (caculate(this.hoa, this.ly, this.toan) < caculate(another.getHoa(),
				another.getLy(), another.getToan())) {
			return -1;
		} else if (caculate(this.hoa, this.ly, this.toan) == caculate(
				another.getHoa(), another.getLy(), another.getToan())) {
			return 0;
		} else {
			return 1;
		}
	}
}
