package RMIExample;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class TCPServer {

    static void serve() throws RemoteException, MalformedURLException {
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        StudentImpl studentImpl = new StudentImpl();
        StudentRemote stub = (StudentRemote) UnicastRemoteObject.exportObject(studentImpl, 0);
        registry.rebind("Student", stub);
        System.out.println("Server Ready");
    }

    public static void main(String[] args) {
        try {
            serve();
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
