package com.hoanghiep.session3tut2;

public class Job {

	private String job;
	private int hour;
	private int minute;
	private int second;
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	@Override
	public String toString(){
		return this.job + "\n" + this.hour + ":" + this.minute + ":" + this.second; 
	}
}
