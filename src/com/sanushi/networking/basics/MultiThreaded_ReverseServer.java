package com.sanushi.networking.basics;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreaded_ReverseServer {

    public static void main(String[] args) {
        int port = 6868;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("MultiThreaded ReverseServer is listening on port " + port);

            // The server will run forever
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                // Each client socket is handled by a new thread
                new ServerThread(socket).start();
            }
        }
        catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}

class ServerThread extends Thread {
    private Socket socket;

    public ServerThread( Socket socket ) {
        this.socket = socket;
    }

    public void run() {
        String text = "";
        do {
            try {
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

            } catch (IOException e) {
                System.out.println("I/O error: " + e.getMessage());
                e.printStackTrace();
            }
        } while ( !text.equalsIgnoreCase("bye") );
    }


    // Method 2 - Returns the reverse string
    public String reverseString(String str) {
        char[] chars = str.toCharArray();
        String reverseWord = "";
        for (int i=chars.length-1; i>=0; i--) {
            reverseWord += chars[i];
        }
        return reverseWord;
    }


}
