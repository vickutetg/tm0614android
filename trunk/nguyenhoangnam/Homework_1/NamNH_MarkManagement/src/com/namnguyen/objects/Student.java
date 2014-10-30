package com.namnguyen.objects;

public class Student {
	private String 	name;
	private int		id;
	private String	className;
	
	private float	markBasicJava;
	private float	markAdvJava;
	private float	markCSharp;
	private float	markAdvCSharp;
	private float	markRDBMS;
	private float	markSQL2008;
	
	public Student() {}
	
	public Student(String name, int id, String className, 
			float markBasicJava, float markAdvJava,	float markCSharp, float markAdvCSharp, 
			float markRDBMS, float markSQL2008) {
		
		setName(name);
		setID(id);
		setClassName(className);
		
		setMarkBasicJava(markBasicJava);
		setMarkAdvJava(markAdvJava);
		setMarkCSharp(markCSharp);
		setMarkAdvCSharp(markAdvCSharp);
		setMarkRDBMS(markRDBMS);
		setMarkSQL2008(markSQL2008);
	}
	
	// Setter
	public void setName(String name) 					{ this.name = name; }
	public void setID(int id) 							{ this.id = id; }
	public void setClassName(String className) 			{ this.className = className; }
	
	public void setMarkBasicJava(float markBasicJava) 	{ this.markBasicJava = markBasicJava; }
	public void setMarkAdvJava(float markAdvJava)		{ this.markAdvJava = markAdvJava; }
	public void setMarkCSharp(float markCSharp)			{ this.markCSharp = markCSharp; }
	public void setMarkAdvCSharp(float markAdvCSharp)	{ this.markAdvCSharp = markAdvCSharp; }
	public void setMarkRDBMS(float markRDBMS)			{ this.markRDBMS = markRDBMS; }
	public void setMarkSQL2008(float markSQL2008)		{ this.markSQL2008 = markSQL2008; }
	
	// Getter
	public String getName() 							{ return name; }
	public int getID() 									{ return id; }
	public String getClassName() 						{ return className; }
	
	public float getMarkBasicJava()						{ return markBasicJava; }
	public float getMarkAdvJava()						{ return markAdvJava; }
	public float getMarkCSharp()						{ return markCSharp; }
	public float getMarkAdvCSharp()						{ return markAdvCSharp; }
	public float getMarkRDBMS()							{ return markRDBMS; }
	public float getMarkSQL2008()						{ return markSQL2008; }

	public float getAvgMark()							{ return (markBasicJava + markAdvJava + markCSharp + markAdvCSharp + markRDBMS + markSQL2008) / 6; }
}
