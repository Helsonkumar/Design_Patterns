package creational;

//** Clonig object  : Creates a new object by copying the attributes of the object being cloned.
//** clone is the method of the ""Object"" class
//** But the object must implement Clonable (tag) interface . it does not contains any method though.Else we get CloneNotSupported Exception
//** While copying object the constructor of the Object being cloned is NOT used.
//** It is not higly recommneded to use this pattern of object creation.


import java.sql.Struct;

public class Clone {

    public static void main(String[] args) {
        Student student = new Student();
        student.name = "helson_Clone";

        Student student1 = student.clone();
        System.out.println(student1.name);

        System.out.println(student.equals(student1));

    }
}


class Student implements  Cloneable{

    String name;

    public Student clone() {
        Student student = null;
        try {
            student = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return student;
    }

}
