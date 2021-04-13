package behavioural;


//* Command Design Pattern
//** Used when  : We need to encapsulate a method call into an Object
//** This would help us to treat method calls(commands) as First Class values and pass these commands as parameters to other methods.
//** We can implement pattern like : Undoing or piling set of commands(method calls) in a stack and defering their execution at later point of time or in scheduled way.
//** Use this pattern when U want to introduce reversible pattern like undoing a command for some reason.
//** A Command object must contain all needed parameters to execute a method call.
//** We have Invoker(Remote) + Command(Instructions) + Receiver (Light : Command executor)

import javax.sound.midi.Receiver;
import java.util.LinkedList;
import java.util.List;


//* Similar treating Functions as first class values in FP programming
public class Command {

    //** This Acts as Client  : Client creates a command and inject it into an Invoker . Command is constructed with a receiver
    public static void main(String[] args) {

        List<Cmd> commnd_arr = new LinkedList<Cmd>();

        //** Create Executor(Receiver)
        Executor light = new Light();
        Executor TV = new TV();

        /*//** Create Commnd with the Receiver
        Cmd cmd1 = new ON(light, "11", "121");
        Cmd cmd2 = new OFF(light, "200", "122");
        Cmd cmd3 = new UP(light, "112", "142");
        Cmd cmd4 = new DOWN(light, "191", "152"); */



       /* //**Create an invoker and attach the commnd during runtime
        Invoker invoker = new Invoker();
        invoker.setCommand(cmd1);
        invoker.executeCommand(commnd_arr);
        invoker.setCommand(cmd2);
        invoker.executeCommand(commnd_arr);
        invoker.setCommand(cmd3);
        invoker.executeCommand(commnd_arr);
        invoker.setCommand(cmd4);
        invoker.executeCommand(commnd_arr);

        for (Cmd cmd : commnd_arr) {
            System.out.println(cmd);
        }
        */

        //** Create Commnd with the Receiver
        Cmd cmd11 = new ON(TV, "11", "121");
        Cmd cmd21 = new OFF(TV, "200", "122");
        Cmd cmd31 = new UP(TV, "12", "142");
        Cmd cmd41 = new DOWN(TV, "191", "152");

        //**Create an invoker and attach the commnd during runtime
        Invoker invoker2 = new Invoker();
        invoker2.setCommand(cmd11);
        invoker2.executeCommand(commnd_arr);
        invoker2.setCommand(cmd21);
        invoker2.executeCommand(commnd_arr);
        invoker2.setCommand(cmd31);
        invoker2.executeCommand(commnd_arr);
        invoker2.setCommand(cmd41);
        invoker2.executeCommand(commnd_arr);

        for (Cmd cmd : commnd_arr) {
            System.out.println(cmd);
        }


    }
}


//**Invoker : Composes of Commnd interface so any commnd can be injected during runtime
//** The receiver actually executes the operation encoded in a command
class Invoker {

    Cmd command;

    public void setCommand(Cmd cmd) {
        this.command = cmd;
    }

    public void executeCommand(List commnd_arr) {
        command.execute(commnd_arr);
    }

    public void undoCommnd(List command_arr) {
        command.undo(command_arr);
    }

}


//* Commnd interface has various concrete classes . Each must pass the request or work to the given executor (Receiver)
//* This must be like an Object which encapsulates a method call : Object  + Method to be called + Params for that method
//* A commnd must habdle how a command is passed to the receiver. But the receiver actually does the work
interface Cmd {

    void execute(List commnd_arr);

    void undo(List commnd_arr);


}


class ON implements Cmd {

    Executor executor;
    String param1;
    String param2;

    public ON(Executor executor, String param1, String param2) {
        this.executor = executor;
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public void execute(List commnd_arr) {
        System.out.println("Executing ON");
        if (executor.operation(param1, param2)) {
            System.out.println("Command executed sucessfully by  " + executor);
            commnd_arr.add(this);
        } else
            System.out.println("Command execution failed  by  " + executor);


    }


    @Override
    public void undo(List commnd_arr) {

        System.out.println("Undoing command");

        Cmd cmd = (Cmd) commnd_arr.remove(commnd_arr.size() - 1);
        //**Implement steps to undo the operation performed
    }

    @Override
    public String toString() {
        return "ON{}";
    }
}


class OFF implements Cmd {

    Executor executor;
    String param1;
    String param2;

    public OFF(Executor executor, String param1, String param2) {
        this.executor = executor;
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public void execute(List commnd_arr) {
        System.out.println("Executing OFF");
        if (executor.operation(param1, param2)) {
            System.out.println("Command executed sucessfully by  " + executor);
            commnd_arr.add(this);
        } else
            System.out.println("Command execution failed  by  " + executor);


    }

    @Override
    public void undo(List commnd_arr) {

        System.out.println("Undoing command");

        Cmd cmd = (Cmd) commnd_arr.remove(commnd_arr.size() - 1);
        //**Implement steps to undo the operation performed
    }

    @Override
    public String toString() {
        return "OFF{}";
    }
}


class UP implements Cmd {

    Executor executor;
    String param1;
    String param2;

    public UP(Executor executor, String param1, String param2) {
        this.executor = executor;
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public void execute(List commnd_arr) {
        System.out.println("Executing UP");
        if (executor.operation(param1, param2)) {
            System.out.println("Command executed sucessfully by  " + executor);
            commnd_arr.add(this);
        } else
            System.out.println("Command execution failed  by  " + executor);

    }

    @Override
    public void undo(List commnd_arr) {

        System.out.println("Undoing command");

        Cmd cmd = (Cmd) commnd_arr.remove(commnd_arr.size() - 1);
        //**Implement steps to undo the operation performed
    }

    @Override
    public String toString() {
        return "UP{}";
    }
}


class DOWN implements Cmd {

    Executor executor;
    String param1;
    String param2;

    public DOWN(Executor executor, String param1, String param2) {
        this.executor = executor;
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public void execute(List commnd_arr) {
        System.out.println("Executing DOWN");
        if (executor.operation(param1, param2)) {
            System.out.println("Command executed sucessfully by  " + executor);
            commnd_arr.add(this);
        } else
            System.out.println("Command execution failed  by  " + executor);

    }

    @Override
    public void undo(List commnd_arr) {
        System.out.println("Undoing command");

        Cmd cmd = (Cmd) commnd_arr.remove(commnd_arr.size() - 1);
        //**Implement steps to undo the operation performed
    }

    @Override
    public String toString() {
        return "DOWN{}";
    }
}


//** Executor holds some business logic
//** This would actually execute a given command
interface Executor {

    boolean operation(String param1, String param2);

}

class Light implements Executor {


    @Override
    public boolean operation(String param1, String param2) {

        return Integer.parseInt(param1) > 10 ? true : false;
    }

    @Override
    public String toString() {
        return "Light";
    }


}

class TV implements Executor {

    @Override
    public boolean operation(String param1, String param2) {
        return Integer.parseInt(param1) > 20 ? true : false;
    }

    @Override
    public String toString() {
        return "Television";
    }
}


