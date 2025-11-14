package server;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException { // "throws IOException" significa che se non riesce ad aprire il serverSocket, dà errore
        int port = 6789; // porta su cui sta il server
        // creo ed apro il socket del server
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.printf("### Server is running on port %d ###\n\n", port);

        // creo un ciclo while infinito che rimane in ascolto dei client e li accetta quando provano a connettersi
        while (true) {
            // try {
                // accetto il client che prova a connettersi
                Socket clientSocket = serverSocket.accept();
                System.out.println("[*] Client Connected");

                OutputStream clientOutput = clientSocket.getOutputStream();
                // mando al client l'header di connessione riuscita
                clientOutput.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                // stampo sulla pagina il titolo (per modificarlo basta cambiare la scritta da "Welcome" a "Server")
                clientOutput.write("<h1 style=\"text-align: center; font-size: 3rem; font-family: 'Arial', sans-serif;\">Welcome to the Java HTTP Server</h1>\r\n\r\n".getBytes());
                clientOutput.flush();

                // la richiesta di connessione e la connessione in sé sono state completate, quindi stampo messaggi strani con cose che interessano solo agli hacker
                System.out.println("[*] TLS Completed");
                // chiudo la connessione con il client finché esso non completa una nuova richiesta sul server
                clientOutput.close();
                System.out.println("[*] Client Connection Closed");
            // } catch () {
            //     serverSocket.close();
            // }
        }
    }
}
