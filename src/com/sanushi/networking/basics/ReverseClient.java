package com.sanushi.networking.basics;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ReverseClient {

    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 6868;

        // Client opens a connection to the server
        try (Socket socket = new Socket(hostname, port)) {
            Scanner sc = new Scanner(System.in);
            String text = "";

            // Client asks input & prints the server’s response until you type ‘bye’ to terminate it.
            System.out.print("Enter text: ");
            text = sc.nextLine();

            do {
                // Send the user input to the server
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(text);

                // Read data sent from the server i.e the reversed string
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String reversedString = reader.readLine();
                System.out.println(reversedString);

                System.out.print("Enter text: ");
                text = sc.nextLine();

            } while( !text.equalsIgnoreCase("bye") );

            sc.close();
            
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {
            // If the server is down
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

}
