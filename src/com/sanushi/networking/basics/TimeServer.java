package com.sanushi.networking.basics;

import java.io.*;
import java.net.*;
import java.util.Date;

/**
 * This program demonstrates a simple TCP/IP socket server.
 *
 * @author Sanushi Salgado
 */
public class TimeServer {

    public static void main(String[] args) {
//        if (args.length < 1) return;

//        int port = Integer.parseInt(args[0]);
        int port = 6868;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("TimeServer is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                // Send data to the client
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(new Date().toString());
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
