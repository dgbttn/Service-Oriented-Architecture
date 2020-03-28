package RMIExample;

import SocketExample.Student;
import SocketExample.StudentManager;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class TCPServer {

    static void setup() {
//        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
//        System.setProperty("java.rmi.server.codebase", IStudentInformation.class.getProtectionDomain().getCodeSource().getLocation().toString());
//        System.out.println(IStudentInformation.class.getProtectionDomain().getCodeSource().getLocation().toString());
    }

    static void serve() throws RemoteException, MalformedURLException {
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        StudentImpl studentImpl = new StudentImpl();

        StudentRemote studentRemote = (StudentRemote) UnicastRemoteObject.exportObject(studentImpl, 0);

        registry.rebind("Student", studentRemote);
        System.out.println("Server Ready");
    }

    public static void main(String[] args) {
        try {
            setup();
            serve();
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
