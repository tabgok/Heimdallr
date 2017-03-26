package com.tabgok.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;


public class MachineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String machine = gson.toJson(Gateway.machine);
        response.setStatus(HttpStatus.OK_200);
        response.setContentType("text/plain;charset=utf-8");
        try {
            response.getWriter().println(machine);
        } catch (IOException ex) {
            Logger.getLogger(MachineServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
