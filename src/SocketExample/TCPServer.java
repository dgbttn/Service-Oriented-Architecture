package SocketExample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class TCPServer {

    private static ServerSocket server;
    private static Socket connection;
    private static BufferedReader reader;
    private static BufferedWriter writer;
    public static int port = 6789;
    private static Hashtable<String, Student> studentList = StudentManager.list;

    static void initServerAndWaitForConnection() throws IOException {
        server = new ServerSocket(port);
        System.out.println("Hosting local server in port " + port);

        connection = server.accept();
        System.out.println("Accepted connection from port " + connection.getPort());

        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
    }

    static void serve() throws IOException {
        while (true) {
            String input = reader.readLine().trim();
            System.out.println("Received: " + input);
            if (input.equals("quit")) return;
            Student student = studentList.get(input);
            sendResponse((student == null) ? "No student found!" : student.toString());
        }
    }

    static void sendResponse(String studenInfo) throws IOException {
        writer.write(studenInfo);
        writer.newLine();
        writer.flush();
    }

    static void closeServer() throws IOException {
        reader.close();
        writer.close();
        server.close();
        System.out.println("Server closed!");
    }

    public static void main(String[] args) throws IOException {
        initServerAndWaitForConnection();
        serve();
        closeServer();
    }
}
