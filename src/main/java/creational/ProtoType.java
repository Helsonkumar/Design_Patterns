package creational;

import sun.security.krb5.Config;

//* ProtoType pattern .
//** Used when there is a minimal diff between the objects to be created.
//* So we resue the existing objects attributes to create new object and make te minimal changes on top of that.
//** The object creation is hidden to the client. They are given with a genaral method ("from") which takes another object as argument
public class ProtoType {

    public static void main(String[] args) {

        //* For each of the below box the config is same except the name.
        //* This looks highly verbose to the client. So will use Prototype patern to hide this object creation
        Configuration config1 = new Configuration();
        config1.setName("helson").setSize(100).setLeftSide(30).setRightSide(30).setTopSide(1000).setBottomSide(1000);
        Box box1 = new Box(config1);

      /*  Configuration config2 = new Configuration();
        config2.setName("Melinda");
        config2.setSize(100);
        config2.setLeftSide(30);
        config2.setRightSide(30);
        config2.setTopSide(100);
        config2.setBottomSide(100);
        Box box2 = new Box(config2);*/


        //** We use the prototype pattern to create new objects here
        //** The existing config object is reused to create new config object here : We use ""from" method
        Configuration config2 = Configuration.from(config1).setName("Naveena");
        System.out.println(config2.getBottomSide());
        System.out.println(config1.getName());
        System.out.println(config2.getName());
        Box box2 = new Box(config2);

        //** We shall use clone method to create a new config object from an existing config object
        Configuration config3 = config2.clone().setName("Jose");
        System.out.println(config2.getName());
        System.out.println(config3.getName());
        Box box3 = new Box(config3);

    }

}


//* The construction of a box is decided by its config.
//** Diff config can create box with diff dimensions
class Box {

    private Configuration config;

    public Box(Configuration config) {
        this.config = config;
    }

}

//** Implement Clonable interface to treat the object as Clonable
class Configuration implements Cloneable {

    String name;
    int size;
    int leftSide;
    int rightSide;
    int topSide;
    int bottomSide;

    public Configuration() {
    }

    public String getName() {
        return name;
    }

    public Configuration setName(String name) {
        this.name = name;
        return this;
    }

    public int getSize() {
        return size;
    }

    public Configuration setSize(int size) {
        this.size = size;
        return this;
    }

    public int getLeftSide() {
        return leftSide;
    }

    public Configuration setLeftSide(int leftSide) {
        this.leftSide = leftSide;
        return this;
    }

    public int getRightSide() {
        return rightSide;
    }

    public Configuration setRightSide(int rightSide) {
        this.rightSide = rightSide;
        return this;
    }

    public int getTopSide() {
        return topSide;
    }

    public Configuration setTopSide(int topSide) {
        this.topSide = topSide;
        return this;
    }

    public int getBottomSide() {
        return bottomSide;
    }

    public Configuration setBottomSide(int bottomSide) {
        this.bottomSide = bottomSide;
        return this;
    }

    public static Configuration from(Configuration config) {

        Configuration config2 = new Configuration();
        config2.setBottomSide(config.getBottomSide());
        config2.setLeftSide(config.getLeftSide());
        config2.setRightSide(config.getRightSide());
        config2.setTopSide(config.getTopSide());
        config2.setSize(config.getSize());
        config2.setName(config.getName());

        return config2;
    }


    //** This is another way to create a new object from the copy of an existing object.
    public Configuration clone() {

        Configuration config = null;
        try {
            //** We need to cast the output of clone (Object --> Configuration)
            config = (Configuration) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return config;

    }


}
