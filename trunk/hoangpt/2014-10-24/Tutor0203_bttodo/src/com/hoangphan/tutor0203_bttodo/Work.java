package com.hoangphan.tutor0203_bttodo;

public class Work {
	//entity, bean class
	private String name;
	private int hour;
	private int minute;
	
	public Work(String name, int hour, int minute) {
		super();
		this.name = name;
		this.hour = hour;
		this.minute = minute;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int calulateMinutes(){
		return hour*60+minute;
	}
	
	//use 1 collection in utility
	//@Override
	public int compareTo(Work another) {
		int mThis = this.calulateMinutes();
		int mAnother = another.calulateMinutes();
		
		if(mThis>mAnother){
			return 1;
		} else if (mThis == mAnother) {
			return 0;
		} else {
			//>->1, =->0, <->-1
			return -1;
		}
	}

	@Override
	public String toString() {
		return hour+":"+minute+" - "+name+".";
	}
}
