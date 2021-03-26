package creational;


//** Builder Pattern
//** Used to address issue whilst creating object with more than one constructors. Each with diff parameter list.
//** The extending constructors are defined as Telescophic constructors.
//** Builder  : Helps us to construct an object with more arguments fluently.

//** In our example we build a Person object which is defined various attributes. Adult or Chidren.
//** An adult cannot go to school and must have age > 18
//** A child cannot have a workplace and must have age < 18
//** So we need to build a person meeting these conditions
public class Builder {

    public static void main(String[] args) {

        Person person = new Person.Builder("Helson").setBirthplace("Chennai").setAsAdult(33).build();
        System.out.println(person.getSchool());
    }

}

class Person {

    //**Below are the attributes of a person class.
    //**Few of these attributes are optional for some Person. Some of them are mandatory for all Person
    private String name;
    private int age;
    private String birthplace;
    private String school;
    private String workplace;

    //* Making the Person constructors Private and not accesible to the external client
    private Person() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public String getSchool() {
        return school;
    }

    public String getWorkplace() {
        return workplace;
    }

    //** Builder class to start building the Person object
    public static class Builder {
        Person person;

        //* name is mandatory : So defined this in constructor
        public Builder(String name) {
            person = new Person();
            person.name = name;
        }

        //** Birthplace is optional for a person
        public Builder setBirthplace(String birthplace) {
            person.birthplace = birthplace;
            return this;
        }

        //* Method to define the Person as Adult with a conditionla check on age
        public AdultBuilder setAsAdult(int age) {
            if (age <= 18)
                throw new IllegalArgumentException("Age for an Adult cannot be lesser than 18 years");
            else {
                person.age = age;
                return new AdultBuilder(person);
            }

        }


        //* Method to define a person as Child with a condition to check if the age  < 18.
        public ChildBuilder setAsChild(int age) {
            if (age <= 18)
                throw new IllegalArgumentException("Age of a Chlild cannot be greater than 18");
            else {
                this.person.age = age;
                return new ChildBuilder(person);
            }


        }
    }


    //* Note  : An Adult cannot have School defined
    public static class AdultBuilder {

        Person person;

        AdultBuilder(Person person) {
            this.person = person;
        }

        public AdultBuilder setWorkPlace(String workplace) {
            this.person.workplace = workplace;
            return this;
        }

        public Person build() {
            return this.person;
        }

    }

    //* Note : A child cannot have workplace defined
    public static class ChildBuilder {

        Person person;

        ChildBuilder(Person person) {
            this.person = person;
        }

        public ChildBuilder setSchool(String school) {
            this.person.school = school;
            return this;
        }

        public Person build() {
            return this.person;
        }

    }


}







