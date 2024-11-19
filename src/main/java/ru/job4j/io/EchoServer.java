package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String firstStr = input.readLine();
                    String result;
                    if (firstStr.contains("msg=Exit")) {
                        server.close();
                        break;
                    } else if (firstStr.contains("msg=Hello")) {
                        result = "Hello, dear friend";
                    } else {
                        String[] substr = firstStr.split(" ");

                        result = substr[1].substring(6);
                    }
                    output.write(((result + "\r\n").getBytes()));
                    System.out.println(firstStr);
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();
                }
            }
        }
    }
}