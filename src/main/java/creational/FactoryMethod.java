package creational;

//** Demo to show factorymethod pattern.
//** We have  : Simplefactory , FactoryMethod , Abstractfactory patterns under factory patterns

//** Diff between factorymethod and Abstractfactory
//** factory method is a single method in an abstract class which can be overridden by the subclass.
//** So the decision of the concrete object creation is delegated to the subclass.
//** A common piece of code is shared across the concrete factory subclasses.

//** Abstractfactory has multiple concrete factory classes which hold many factory methods.
//** No code sharing.
public class FactoryMethod {

    public static void main(String[] args) {

        CarFactory carFactory = new UKCarFactory();
        Car car1 = carFactory.createCar();
        System.out.println(car1.getName());

        CarFactory indianFactory = new IndianCarFactory();
        Car car2 = indianFactory.createCar();
        System.out.println(car2.getName());

    }

}


abstract class CarFactory {

    public void setWheel() {
        Car car = createCar();

    }

    public void setLights(Car car) {

    }

    public abstract Car createCar();


}

class UKCarFactory extends CarFactory {


    @Override
    public Car createCar() {
        return new UKCar();
    }


}

class IndianCarFactory extends CarFactory {

    @Override
    public Car createCar() {
        return new IndianCar();
    }


}


interface Car {

    public abstract String getName();

}

class UKCar implements Car {
    @Override
    public String getName() {
        return "UKCar";
    }


}

class IndianCar implements Car {
    @Override
    public String getName() {
        return "IndianCar";
    }

}

