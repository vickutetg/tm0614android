package marktmanagement;

public class Student implements Comparable<Student> {

    String nameStudent;
    String classStudent;
    String id;
    String rank;
    double basicJava;
    double advanceJava;
    double C;
    double advanceC;
    double rdbms;
    double sql2008;
    double averageMark;

    public String getnameStudent() {
        return nameStudent;
    }

    public void setnameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getClassStudent() {
        return classStudent;
    }

    public void setClassStudent(String classStudent) {
        this.classStudent = classStudent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public double getBasicJava() {
        return basicJava;
    }

    public void setBasicJava(double basicJava) {
        this.basicJava = basicJava;
    }

    public double getAdvanceJava() {
        return advanceJava;
    }

    public void setAdvanceJava(double advanceJava) {
        this.advanceJava = advanceJava;
    }

    public double getC() {
        return C;
    }

    public void setC(double C) {
        this.C = C;
    }

    public double getAdvanceC() {
        return advanceC;
    }

    public void setAdvanceC(double advanceC) {
        this.advanceC = advanceC;
    }

    public double getRdbms() {
        return rdbms;
    }

    public void setRdbms(double rdbms) {
        this.rdbms = rdbms;
    }

    public double getSql2008() {
        return sql2008;
    }

    public void setSql2008(double sql2008) {
        this.sql2008 = sql2008;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    @Override
    public int compareTo(Student compareStudent) {
        double compareAveragemark = ((Student) compareStudent).getAverageMark();
        return (int) ((- this.averageMark + compareAveragemark) * 100);
    }
}
