package creational;

import sun.reflect.Reflection;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//** Use Singleton to have just one instance of a class and give global access to that class for all the threads who need to access.
//** used for use case like maintaing  global ledger, look up, caches, global Configs, etc
public class Singleton {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Principal principal1 = Principal.getSingleTonInstance();
        System.out.println(principal1.hashCode());

        Principal principal2 = Principal.getSingleTonInstance();
        System.out.println(principal2.hashCode());

        System.out.println(principal1.equals(principal2));

        //** We have made the constructor private :
        // But still using refelection we can create another instance of the same class which breaks the singleton principle of the class
        Constructor constructor = principal1.getClass().getDeclaredConstructor(new Class[0]);
        //** Here we make the private constructor to be accessible
        constructor.setAccessible(true);
        //** We ended up creating another instance of the class even though the constructor is private
        Principal principal3 = (Principal) constructor.newInstance();

        System.out.println(principal1.equals(principal3));
        System.out.println(principal1.hashCode());
        System.out.println(principal3.hashCode());


        //**accessing singleton instance from enum
        Principal1 p1 = Principal1.INSTANCE;
        System.out.println(p1.hashCode());
        Principal1 p2 = Principal1.INSTANCE;
        System.out.println(p1.hashCode());

        System.out.println(p1.equals(p2));

        //** If we try creating instance of an enum we get exception
        /*Principal1 p3 =  p2.getClass().getDeclaredConstructor(new Class[0]).newInstance();
        System.out.println(p3.hashCode());
*/

        //** Let's check if we can serialize and deerialize the enum
        //** We get the same object with the same hashCode post Deser if that is an enum
        try (FileOutputStream fos = new FileOutputStream("helson.ser")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(p1.hashCode());
            oos.writeObject(p1);
            oos.close();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream("helson.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Principal1 p4 = (Principal1) ois.readObject();
            ois.close();
            fis.close();

            System.out.println(p4.hashCode());
            System.out.println(p4.equals(p1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        //* Lets check if we get the same object with the same hashCode if that is a general class
        //** We could see that after SerDe we get objects with diff hashCode
        try {
            FileOutputStream fos2 = new FileOutputStream("helson2.ser");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(principal1);
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(principal1.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis2 = new FileInputStream("helson2.ser");
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            Principal p42 = (Principal) ois2.readObject();
            ois2.close();
            fis2.close();

            System.out.println(p42.hashCode());
            System.out.println(p42.equals(principal1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}


class Principal implements Serializable {

    //** Make the attributes of the Object private and final if initialized
    private final String name = "Milan";
    ;
    static Principal principal;

    //** Explicitly make the constructor Private ,not to let mutiple instance of Principal to be created by each clients.
    private Principal() {
    }

    //* Make this method "synchornized" to avoid race condition im multithread env
    //* But still if this object is serialized and deserialized we end up with more than one instance of this class.(But it barely occurs)
    //* But the general pattern is to use "enum" with this Singleton instance instead of this method
    public static synchronized Principal getSingleTonInstance() {

        //** Here we Lazily create the instance when it is accessed for the first time.
        //* Check if an instance is already there else create one and reuse it in the next invocation
        if (principal == null)
            principal = new Principal();

        return principal;
    }


}

//** Best practice to create any singleton instance
//** Diff ways to create instance of a Class  : Constructor  , Refelection  , Serialization / Deserailization
//** To overcome the issue with the private constructor we need to use ""enum""
//** enums are inherently serializable, we don't need to implement it with a serializable interface.
//** Post SerDe we get the same singleton insatnce if it is an enum unlike general class
//** Thus, this method is recommended as the best method of making singletons in Java.
enum Principal1 {

    INSTANCE;

    public String getName() {
        return "Melinda Marian";
    }


}