package com.oceanview.controller;

import com.google.gson.Gson;
import com.oceanview.model.User;
import com.oceanview.service.AuthService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AuthController implements HttpHandler {

    private final AuthService authService = new AuthService();
    private final Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();

        if (method.equalsIgnoreCase("POST") && path.endsWith("/register")) {
            handleRegister(exchange);
        } else if (method.equalsIgnoreCase("POST") && path.endsWith("/login")) {
            handleLogin(exchange);
        } else {
            sendResponse(exchange, 404, "Endpoint Not Found");
        }
    }

    private void handleRegister(HttpExchange exchange) throws IOException {

        User user = gson.fromJson(
                new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8),
                User.class
        );

        String result = authService.register(user.getUsername(), user.getPassword());

        sendResponse(exchange, 200, result);
    }

    private void handleLogin(HttpExchange exchange) throws IOException {

        User user = gson.fromJson(
                new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8),
                User.class
        );

        boolean success = authService.login(user.getUsername(), user.getPassword());

        if (success) {
            sendResponse(exchange, 200, "Login Successful");
        } else {
            sendResponse(exchange, 401, "Invalid Credentials");
        }
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response)
            throws IOException {

        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
