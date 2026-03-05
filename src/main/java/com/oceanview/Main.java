package com.oceanview;


import com.sun.net.httpserver.HttpServer;
import com.oceanview.controller.AuthController;


import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Auth
        server.createContext("/api/register", (new AuthController()));
        server.createContext("/api/login", (new AuthController()));

        server.setExecutor(null);
        server.start();

        System.out.println("Ocean View Resort Server Started on port 8000 ✅");
    }
}