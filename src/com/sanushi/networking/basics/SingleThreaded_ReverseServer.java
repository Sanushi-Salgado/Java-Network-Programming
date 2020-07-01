package com.sanushi.networking.basics;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreaded_ReverseServer {

    public static String reverseString(String str) {
        char[] chars = str.toCharArray();
        String reverseWord = "";
        for (int i=chars.length-1; i>=0; i--){
            reverseWord += chars[i];
        }
        return reverseWord;
    }

    public static void main(String[] args) {
        int port = 6868;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("ReverseServer is listening on port " + port);

            // The server will run forever
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                String text = "";
                do {
                    // Read data sent from the client i.e. the user entered string
                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader( new InputStreamReader(input) );
                    text = reader.readLine();

                    // Reverse the string sent by the client
                    // Method 1
                    String reversedWord = new StringBuilder(text).reverse().toString();

                    // Method 2
                    // String reversedWord = reverseString(text);

                    // Send data back to the client i.e. the reversed string
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    writer.println("Server: " + reversedWord);

                } while ( !text.equalsIgnoreCase("bye") );
            }
        }
        catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
