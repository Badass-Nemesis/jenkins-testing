package com.example;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {
        // Use minimal thread pool (1 thread for t2.micro)
        HttpServer server = HttpServer.create(new InetSocketAddress(9090), 1); // <- Backlog set to 1
        server.createContext("/", exchange -> {
            String response = "Hello from lightweight Java HTTP Server-New!";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });
        server.start();
        System.out.println("Server running on http://localhost:9090 (Press Ctrl+C to stop)");
    }
}
