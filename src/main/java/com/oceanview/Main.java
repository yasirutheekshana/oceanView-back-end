package com.oceanview;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.setExecutor(null);
        server.start();

        System.out.println("Ocean View Resort Server Started on port 8000 ✅");
    }
}