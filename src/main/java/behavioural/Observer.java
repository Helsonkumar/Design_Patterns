package behavioural;

import java.util.ArrayList;
import java.util.List;

//**Observer pattern
//**Use when an Object(Publisher) state update must be communicated across various subscribers
public class Observer {

    public static void main(String[] args) {
        Publisher publisher = new Publisher();

        Subscriber subs1 = new Helson();
        Subscriber subs2 = new Marian();

        publisher.add_subs(subs1);
        publisher.add_subs(subs2);

        publisher.update_state("Tmrw is Sunday!!!");
        publisher.update_state("How are U?");
    }
}


class Publisher {

    List<Subscriber> subcribers = new ArrayList<Subscriber>();
    String state_update;

    //**When there is a state change we need to update the subscribers
    public void update_state(String value) {
        this.state_update = value;
        send_notification();
    }

    public void send_notification() {
        for (Subscriber s : subcribers) {
            s.update(state_update);
        }
    }

    //** Methods to add and remove subscribers from the list of subscribers maintained
    public void add_subs(Subscriber s) {
        subcribers.add(s);
    }

    public void remove_subs(Subscriber s) {
        subcribers.remove(s);
    }
}


interface Subscriber {

    void update(String state_update);

}


class Helson implements Subscriber {

    @Override
    public void update(String state_update) {
        System.out.println("This is the updated state from Publisher for Helson: " + state_update);
    }


}

class Marian implements Subscriber {

    @Override
    public void update(String state_update) {
        System.out.println("This is the updated state from Publisher for Marian: " + state_update);
    }
}


