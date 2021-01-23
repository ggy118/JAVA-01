package com.pristine.nio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName : HttpServer1
 * @Description :
 * @Author : Pristine
 * @Date: 2021-01-17 23:02
 */
public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        while(true) {
            Socket socket = serverSocket.accept();
            System.out.println(String.format("client %s connect!",socket.getInetAddress().getHostAddress()));
            service(socket);
        }
    }

    public static void service(Socket socket) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf8");
            String body = "hello nio";
            printWriter.println("Content-Length:"+body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
