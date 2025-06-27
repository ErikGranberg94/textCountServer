package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if(args.length < 2){
            System.err.println("Missing filepath or port number");
            return;
        }

        final var fileName = args[0];
        final var portNumber = Integer.parseInt(args[1]);
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while(true) {
            Socket clientSocket;
            System.out.println("Server is running and waiting for client connection...");

            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Client connected!");

            Scanner reader;
            try {
                reader = new Scanner(new File(fileName));
                readFile(clientSocket, reader);
                reader.close();
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }

            try {
                clientSocket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private static void readFile(Socket clientSocket, Scanner reader) {
        PrintWriter out;

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (reader.hasNextLine()){
            out.println(reader.nextLine());
        }
    }
}
