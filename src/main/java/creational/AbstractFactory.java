package creational;

//** AbstractFactory Pattern
//** Provide an interface to create families of related products or object instead of concrete object types.
//** Used when we need to provide a combinations or set of more than one objects.
//** Each set refers a family of related objects

//** Helps in UI design  : MacOs  + Windows + Linux  : Ui Ctrls + Buttons
//** For a given ENV : we get Tuple(config , CyberArkConn)

//The Abstract Factory design pattern solves problems like:
//        How can an application be independent of how its objects are created?
//        How can a class be independent of how the objects it requires are created?
//        How can families of related or dependent objects be created?

public class AbstractFactory {

    public static void main(String[] args) {

        System.out.println(new Product1Factory().createProductA());
        System.out.println(new Product1Factory().createProductB());

        System.out.println(new Product12Factory().createProductA());
        System.out.println(new Product12Factory().createProductB());

    }
}


interface ProductA {
}

class ProductA1 implements ProductA {
    @Override
    public String toString() {
        return "ProductA1";
    }
}

class ProductA2 implements ProductA {
    @Override
    public String toString() {
        return "ProductA2";
    }
}


interface ProductB {
}

class ProductB1 implements ProductB {
    @Override
    public String toString() {
        return "ProductB1";
    }
}

class ProductB2 implements ProductB {
    @Override
    public String toString() {
        return "ProductB2";
    }
}


//** This is the interface to create set of related objects
interface AbstractFactoryInterface {

    ProductA createProductA();

    ProductB createProductB();

}

//** Concrete Factory class to create Families of related concrete class (A1,B1)
class Product1Factory implements AbstractFactoryInterface {

    @Override
    public ProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB1();
    }
}


//** Concrete Factory class to create Families of related concrete class (A2,B2)
class Product2Factory implements AbstractFactoryInterface {

    @Override
    public ProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB2();
    }
}


//** Concrete Factory class to create Families of related concrete class (A1,B2)
class Product12Factory implements AbstractFactoryInterface {

    @Override
    public ProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB2();
    }
}



