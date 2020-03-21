package SocketExample;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    private static Socket client;
    private static BufferedWriter request;
    private static BufferedReader response;

    static void initSocket() throws IOException {
        client = new Socket("localhost", TCPServer.port);
        System.out.println("Start client socket at port " + client.getLocalPort());
        request = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        response = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    static void sendRequest(String input) throws IOException {
        request.write(input);
        request.newLine();
        request.flush();
    }

    static void run() throws IOException {
        Scanner console = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the student id: ");
            String input = console.nextLine().trim();
            sendRequest(input);
            if (input.equals("quit")) return;
            receiveResponse();
        }
    }

    static void receiveResponse() throws IOException {
        while (!response.ready()) {}
        while (response.ready())
            System.out.println(response.readLine());
    }

    static void closeSocket() throws IOException {
        request.close();
        response.close();
        client.close();
        System.out.println("Client socket closed!");
    }

    public static void main(String[] args) throws IOException {
        initSocket();
        run();
        closeSocket();
    }
}
