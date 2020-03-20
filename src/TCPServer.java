import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class TCPServer {

    private static ServerSocket server;
    public static int port = 6789;
    private static Hashtable<String, Student> studentList = StudentManager.list;

    public static void main(String[] args) throws IOException {
        server = new ServerSocket(port);
        System.out.println("Hosting local server in port " + port);

        Socket connection = server.accept();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

        ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());

        while (true) {
            String input = reader.readLine().trim();
            if (input.equals("quit")) break;

            Student student = studentList.get(input);
            System.out.println(student);

//            output.writeObject(student);
//            output.flush();

            writer.write("This is response!");
            writer.newLine();
            writer.flush();
        }

        reader.close();
        writer.close();
        server.close();
        System.out.println("Server closed!");
    }
}
