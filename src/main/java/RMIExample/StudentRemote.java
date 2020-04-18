package RMIExample;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentRemote extends Remote {
    String getInformation(String studentId) throws RemoteException;
}
