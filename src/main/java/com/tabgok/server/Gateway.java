package com.tabgok.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;


public class Gateway {
    public Gateway(){
        Server server = new Server(8000);
        
        try {
            ServletContextHandler handler = new ServletContextHandler(server, "/epoch");
            handler.addServlet(EpochServlet.class, "/");
            server.start();
        } catch (Exception ex) {
            Logger.getLogger(Gateway.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
