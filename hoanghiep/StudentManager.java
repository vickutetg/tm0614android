import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

public class StudentManager {

	public void addRecord(ArrayList<Student> student) {
		Scanner input = new Scanner(System.in);
		Student person = new Student();
		System.out.print("---name: ");
		String name = input.nextLine();
		person.setName(name);

		System.out.print("---id: ");
		int id = input.nextInt();
		person.setId(id);

		System.out.print("---class: " + input.nextLine());
		String classroom = input.nextLine();
		person.setClassroom(classroom);

		System.out.print("---basic java: ");
		int basicjava = input.nextInt();
		person.setBasicjava(basicjava);

		System.out.print("---advance java: ");
		int advancejava = input.nextInt();
		person.setAdvancejava(advancejava);

		System.out.print("---c#: ");
		int cshape = input.nextInt();
		person.setCshape(cshape);

		System.out.print("---advance c#: ");
		int advancecshape = input.nextInt();
		person.setAdvancecshape(advancecshape);

		System.out.print("---rdbms: ");
		int rdbms = input.nextInt();
		person.setRdbms(rdbms);

		System.out.print("---sql 2008: ");
		int sql = input.nextInt();
		person.setSql(sql);
		double average = (basicjava + advancejava + cshape + advancecshape + rdbms + sql)/6;
		person.setAverage(average);
		String rank;
		if(average >9){
			rank = "excelent";
		}else if(8 < average && average <= 9){
			rank = "good";
		}else if(7 < average && average <= 8){
			rank = "fair";
		}else if(5 <= average && average < 7){
			rank = "ok";
		}else{
			rank = "weak";
		}
		person.setRank(rank);
		System.out.println("-> " + name + " (id=" + id + ") has average mark of " + average + ", rank \'" + rank + "\'");
		student.add(person);
	}

	public void display(ArrayList<Student> student){
		Iterator iterator = student.iterator();
		System.out.println("Name\t\tid\t\tclass\t\taverage\t\trank");
		System.out.println("----------------------------------------------------------------------");
		while(iterator.hasNext()){
			Student person = (Student)iterator.next();			
			System.out.println(person.getName() + "\t\t" + person.getId() + "\t\t" + person.getClassroom() + "\t\t" + person.getAverage() + "\t\t" + person.getRank());
		}
	}
}
