package SocketExample;

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
        System.out.println("Accepted connection from port " + connection.getPort());

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

        while (true) {
            // Receive input from client socket
            String input = reader.readLine().trim();
            System.out.println("Received: " + input);
            if (input.equals("quit")) break;

            Student student = studentList.get(input);

            // Send student information
            writer.write((student == null) ? "No student found!" : student.toString());
            writer.newLine();
            writer.flush();
        }

        // Close server socket
        reader.close();
        writer.close();
        server.close();
        System.out.println("Server closed!");
    }
}
