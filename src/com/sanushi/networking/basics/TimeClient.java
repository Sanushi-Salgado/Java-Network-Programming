package com.sanushi.networking.basics;
import java.net.*;
import java.io.*;

/**
 * This program demonstrates a simple TCP/IP socket client.
 *
 * @author Sanushi Salgado
 */
public class TimeClient {

    public static void main(String[] args) {
//        if (args.length < 2) return;

//        String hostname = args[0];
//        int port = Integer.parseInt(args[1]);

        String hostname = "localhost";
        int port = 6868;

        // Client opens a connection to the server
        try (Socket socket = new Socket(hostname, port)) {
            // Read data sent from the server
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String time = reader.readLine();
            System.out.println(time);

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {
            // If the server is down
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}