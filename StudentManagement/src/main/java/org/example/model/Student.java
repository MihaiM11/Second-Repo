package org.example.model;

import org.example.Exceptii.BugetInvalidException;

import java.util.Objects;

public class Student {

    private int idStudent;
    private String nameStudent;
    private double buget;

    public Student(int idStudent, String nameStudent, double buget) {
        this.idStudent = idStudent;
        this.nameStudent = nameStudent;
        this.buget = buget;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public double getBuget() {
        return buget;
    }

    public void setBuget(double buget) throws BugetInvalidException {
        if(buget<0){
            throw new BugetInvalidException("Studentul nu are destui bani pentru a se inrola la curs ");
        }
        this.buget = buget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return idStudent == student.idStudent && Double.compare(buget, student.buget) == 0 && Objects.equals(nameStudent, student.nameStudent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent, nameStudent, buget);
    }

    @Override
    public String toString() {
        return  idStudent +
                "," + nameStudent +
                "," + buget ;
    }
}
