import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", TCPServer.port);
        System.out.println("Start client socket at port " + client.getLocalPort());
        Scanner console = new Scanner(System.in);

        BufferedWriter request = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        BufferedReader response = new BufferedReader(new InputStreamReader(client.getInputStream()));

        while (true) {
            System.out.print("Enter the student id: ");
            String input = console.nextLine().trim();

            // Send input to server
            request.write(input);
            request.newLine();
            request.flush();

            if (input.equals("quit")) break;

            // Receive response
            String result = response.readLine();
            if (result == null) break;
            System.out.println("Response from server: " + result);
        }
        request.close();
        response.close();
        client.close();
    }
}
