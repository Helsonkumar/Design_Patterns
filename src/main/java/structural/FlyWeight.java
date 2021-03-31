package structural;


//* FlyWeight Pattern
//* "Flyweight is a structural design pattern that lets you fit more objects into the available amount of RAM by sharing common parts of state
//* between multiple objects instead of keeping all of the data in each object."
//* the intrinsic state: the fields that contain unchanging data duplicated across many objects
//* the extrinsic state: the fields that contain contextual data unique to each object
//* We can effectively utilize the RAM space for objects in App

import java.util.HashMap;
import java.util.Map;

//* Idea is to segregate the static and changing properties into two diff classes.
//* So the same intrinsic class would be resued whenever its features are needed
public class FlyWeight {

    public static void main(String[] args) {

        //** Here whilst creating the object the static properties are reused.
        ContextWithUniqueProp emp1 = new ContextWithUniqueProp("Safik" , "Helson" , 33);
        ContextWithUniqueProp emp2 = new ContextWithUniqueProp("Vishnu" , "Melinda" , 27);
        ContextWithUniqueProp emp3 = new ContextWithUniqueProp("Safik" , "Naveen" , 17);
        ContextWithUniqueProp emp4 = new ContextWithUniqueProp("Anto" , "Suresh" , 42);

        System.out.println(emp1.manager.name);
        System.out.println(emp2.manager.name);
        System.out.println(emp3.manager.name);
        System.out.println(emp4.manager.name);

    }
}

//** This is a context class which hold properties which would be unique for each object
class ContextWithUniqueProp {

    String employeeName;
    int age;
    ManagerFlyWeight manager;


    ContextWithUniqueProp(String manager, String employeeName, int age) {
        ManagerFlyWeight mngr = ManagerFactory.getManagerDetails(manager);
        this.manager = mngr;
    }
}

//** This is a class which would holds the intrinsic properties of each objects.ie Manegr info would be repeatable for most of the employees.
//** This is an intrincsic properties
class ManagerFlyWeight {

    String name;
    String designation;
    String reportingTo;

    public ManagerFlyWeight(String name, String designation, String reportingTo) {
        this.name = name;
        this.designation = designation;
        this.reportingTo = reportingTo;
    }
}

//* This is the factory for the given FlyWeight class and it always checks the chache for the existing flyweight object else create new one
class ManagerFactory {

    static Map<String, ManagerFlyWeight> cache = new HashMap();

    public static ManagerFlyWeight getManagerDetails(String manager) {

        ManagerFlyWeight managerDetails = cache.get(manager);

        if (managerDetails == null) {
            ManagerFlyWeight newManager = new ManagerFlyWeight(manager, ManagerFactory.getDesignationFromDB(), ManagerFactory.getReportingToFromDB());
            cache.put(manager, newManager);
            return newManager;
        } else {
            return managerDetails;
        }


    }

    public static String getDesignationFromDB() {
        return "VP";

    }

    public static String getReportingToFromDB() {
        return "Ashwaani";
    }


}
