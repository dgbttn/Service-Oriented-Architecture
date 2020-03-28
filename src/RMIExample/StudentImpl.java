package RMIExample;

import SocketExample.Student;
import SocketExample.StudentManager;
import java.rmi.RemoteException;

public class StudentImpl implements StudentRemote {
    @Override
    public String getInformation(String studentId) throws RemoteException {
        Student student = StudentManager.list.get(studentId);
        return (student == null) ? "No student found!" : student.toString();
    }
}
