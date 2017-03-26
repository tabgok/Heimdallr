package com.tabgok.server;

import com.tabgok.entity.Machine;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;


public class Gateway {
    static Machine machine;
    public Gateway(Machine machine){
        this.machine = machine;
        Server server = new Server(8080);
        
        try {
            ServletContextHandler handler = new ServletContextHandler(server, "/");
            handler.addServlet(EpochServlet.class, "/epoch");
            handler.addServlet(MachineServlet.class, "/machine");
            handler.addServlet(FilesystemServlet.class,"/filesystem");
            
            server.start();
        } catch (Exception ex) {
            Logger.getLogger(Gateway.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
