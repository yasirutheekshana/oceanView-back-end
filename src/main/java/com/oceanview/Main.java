package com.oceanview;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.oceanview.controller.AuthController;

import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Auth
        server.createContext("/api/register", wrapWithCORS(new AuthController()));
        server.createContext("/api/login", wrapWithCORS(new AuthController()));

        server.setExecutor(null);
        server.start();

        System.out.println("Ocean View Resort Server Started on port 8000 ✅");
    }

    public static HttpHandler wrapWithCORS(HttpHandler handler) {
        return exchange -> {

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
                exchange.sendResponseHeaders(204, -1);
                exchange.close();
                return;
            }

            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            handler.handle(exchange);
        };
    }
}