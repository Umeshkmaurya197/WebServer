package com.git;

import com.sun.net.httpserver.HttpServer;


import java.io.IOException;
import java.net.InetSocketAddress;


public class SimpleHttpServer {
    public static int DEFAULT_PORT=9090;
    public static int port;
    private HttpServer httpServer;

    private void start(int port) {
        this.port = port;
        try{
            httpServer = HttpServer.create(new InetSocketAddress(port),0);
            System.out.println(" Server started at "+port);

            // Curl -     http://localhost:9090/
            httpServer.createContext("/",new Handlers.RootHandler());
            // Curl -     http://localhost:9090/echoHeader
            httpServer.createContext("/echoHeader",new Handlers.EchoHeaderHandler());
            // Curl -     http://localhost:9090/echoGet
            httpServer.createContext("/echoGet",new Handlers.EchoGetHandler());
            // Curl -     http://localhost:9090/echoPost
            httpServer.createContext("/echoPost",new Handlers.EchoPostHandler());
            httpServer.setExecutor(null);
            httpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    SimpleHttpServer httpsServer = new SimpleHttpServer();
    httpsServer.start(DEFAULT_PORT);
    }
}
