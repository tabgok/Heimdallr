/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.heimdallr;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileSystemView;

/**
 *
 */
public class Actor implements HttpHandler {
    private Gson gson = new Gson();
    
    public Actor(){
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/isAlive", this);
            server.createContext("/epoch", this);
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(Actor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
            
    public static void main(String args[]){
        Actor a = new Actor();
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        
        String response = null;
        System.out.println(t.getHttpContext().getPath());
        switch (t.getHttpContext().getPath()){
            case "/isAlive":
                response = "";
                break;
            case "/epoch":
                Data epoch = new Data("seconds", "epoch", System.currentTimeMillis()/1000);
                response = gson.toJson(epoch) +"\n";
                break;
            default:
                response = null;
                
        }
        OutputStream os = t.getResponseBody();
        if(response != null){
            t.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
        }else{
            t.sendResponseHeaders(404, 0);
        }
        os.close();
    }
}
