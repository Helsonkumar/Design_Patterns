package structural;


//** Bridge Design Pattern
//* Separate an abstraction (Abstraction) from its implementation (Implementor) by putting them in separate class hierarchies.
//* Implement the Abstraction in terms of (by delegating to) an Implementor object.

/*What problems can the Bridge design pattern solve?
        An abstraction and its implementation should be defined and extended independently from each other.
        A compile-time binding between an abstraction and its implementation should be avoided so that an implementation can be selected at run-time.*/

//** Think of Abstraction and Implemenattion.
//** Whenever an abstraction changes all its implemented subclass would e bounded for a change.
//** When a class is extended or varies in two diff dimesnions(Variants of functionality) then we can maintain two diff heirarche one for each dimesnions.
//** That both the Abstraction and Implementation can vary independently.
//** Instead if Inheritence , we use composition (ref to implemetor class) in the abstraction
//** Due to compostion the implementation can be changed during Runtime unlike Inheritance where te implementation is decided during compile time.
public class Bridge {

    public static void main(String[] args) {

        //** The implementor in the abstractor is changed during runtime
        Remote TVremote = new AdvancedRemote(new TV());
        System.out.println(TVremote.pressLeft());
        System.out.println(TVremote.redButton());

        Remote Radioremote = new AdvancedRemote(new Radio());
        System.out.println(Radioremote.pressLeft());
        System.out.println(Radioremote.redButton());

    }
}

//** Consider Shape and Color example. If both color and shape get extended, then both result in cartesian product of classes.
//** This would be catasrophic if the number of dimensions or the abstract and implementor variants of fucntionality is large.
//** ie. to avoid classes like REdCircle , REDSquare, BlueSquare, BlueCicrle,etc

//*Abstractor : Which defines the abstraction hierarchy
abstract class Remote {

    //** The Remote is composed of Device
    protected Device device;

    String pressUp() {
        return device.pressUp();
    }

    String pressLeft() {
        return device.pressLeft();
    }

    abstract String redButton();
}

//*This is the Refined Abstractor.
class AdvancedRemote extends Remote {

    AdvancedRemote(Device device) {
        super();
        super.device = device;
    }

    @Override
    String redButton() {
        return device.pressRED();
    }
}

//*Implementor
interface Device {

    String pressUp();

    String pressLeft();

    String pressRED();

}


class Radio implements Device {

    @Override
    public String pressUp() {
        return "Radio Vol ++";
    }

    @Override
    public String pressLeft() {
        return "Radio Tune --";
    }

    @Override
    public String pressRED() {
        return "Radio Turned OFF";
    }
}

class TV implements Device {

    @Override
    public String pressUp() {
        return "TV Channel ++ ";
    }

    @Override
    public String pressLeft() {
        return "TV Vol --";
    }

    @Override
    public String pressRED() {
        return "TV DVD mode";
    }
}


