package RMIExample;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class TCPClient {
    private static StudentRemote stub;

    static void setup() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT);
        stub = (StudentRemote) registry.lookup("Student");
    }

    static void run() throws RemoteException {
        Scanner console = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print("Enter the student id: ");
            input = console.nextLine().trim();
            if (input.equals("quit")) break;
            System.out.println(stub.getInformation(input));
        }
        System.out.println("Closed!");
    }

    public static void main(String[] args) {
        try {
            setup();
            run();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
