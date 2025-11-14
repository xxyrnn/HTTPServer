package server;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        final int PORT = 6789; // change this to run the server on a different port
        ServerSocket serverSocket = new ServerSocket(PORT); // open the socket
        System.out.printf("### Server is running on port %d ###\n\n", PORT);

        // infinite loop to listen for new connections and accept them every time we receive one
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("[*] Client Connected");

            OutputStream clientOutput = clientSocket.getOutputStream();
            // send the "successfully connected" header to the client
            clientOutput.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
            // let's print the title on the page (to edit it just change the text)
            clientOutput.write("<h1 style=\"text-align: center; font-size: 3rem; font-family: 'Arial', sans-serif;\">Welcome to the Java HTTP Server</h1>\r\n\r\n".getBytes());
            clientOutput.flush();

            System.out.println("[*] TLS Completed");
            clientOutput.close();
            System.out.println("[*] Client Connection Closed");
        }
    }
}
