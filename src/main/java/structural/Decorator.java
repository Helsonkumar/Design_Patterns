package structural;

//** Decorator Design Pattern
//** When U want t add functionality to an object we generally extend the class of the object into a new class and priovide the new functionality.(Inheritence)
//** But the functionality of the class is decided during runtime and cannot be changed dynamically during runtime for the same class.
//* Decorator pattern would help U alter the functional of a class dynamically during runtime
public class Decorator {

    public static void main(String[] args) {

        Messenger textMsgr = new TextMessenger();
        FaceBookMessenger faceBookMsgr = new FaceBookMessenger(textMsgr);

        //** We have 2 instance of the same class whose functionality is changed Dynamically
        WatsAppMessenger WatsappMsgr1 = new WatsAppMessenger(faceBookMsgr);

        WatsAppMessenger WatsappMsgr2 = new WatsAppMessenger(textMsgr);

        WatsAppMessenger WatsappMsgr3 = new WatsAppMessenger(WatsappMsgr2);

        WatsappMsgr1.sendMessage();
        System.out.println("  ");
        WatsappMsgr2.sendMessage();
        System.out.println("  ");
        WatsappMsgr3.sendMessage();
    }

}


interface Messenger {

    public void sendMessage();

}

class TextMessenger implements Messenger {

    @Override
    public void sendMessage() {
        System.out.println("This is a message from text Messenger");
    }
}

//** But what if we want to define a new functionality dynamically  for a class ?
//** Below is the Decorator Class
class EmailMessenger implements Messenger {

    //** A Class composes an instance of the type which it implements
    Messenger msgr;

    EmailMessenger(Messenger msgr) {
        this.msgr = msgr;
    }

    //* Here the new functionality is wrapped inside an object
    public void sendMessage() {
        if (this.msgr == null) {
            System.out.println("null : This is a message from Email Messenger");
        } else {
            this.msgr.sendMessage();
            System.out.println("This is a message from Email Messenger");
        }

    }
}

class FaceBookMessenger implements Messenger {

    Messenger msgr;

    FaceBookMessenger(Messenger msgr) {
        this.msgr = msgr;
    }

    @Override
    public void sendMessage() {
        if (this.msgr == null) {
            System.out.println("null : This is a message from FaceBook Messenger");
        } else {
            this.msgr.sendMessage();
            System.out.println("This is a message from FaceBook Messenger");
        }

    }
}


class WatsAppMessenger implements Messenger {

    Messenger msgr;

    WatsAppMessenger(Messenger msgr) {
        this.msgr = msgr;
    }

    @Override
    public void sendMessage() {
        if (this.msgr == null) {
            System.out.println("null : This is a message from WatsApp Messenger");
        } else {
            this.msgr.sendMessage();
            System.out.println("This is a message from WatsApp Messenger");
        }

    }
}
