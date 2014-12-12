package com.NguyenTruong.model;

import java.io.Serializable;

public class Food implements Serializable{

	private String name;
	private String dificult;
	private String image;
	private int duration;
	private String material;
	private String cook;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDificult() {
		return dificult;
	}

	public void setDificult(String dificult) {
		this.dificult = dificult;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getCook() {
		return cook;
	}

	public void setCook(String cook) {
		this.cook = cook;
	}

}
