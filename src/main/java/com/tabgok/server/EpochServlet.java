package com.tabgok.server;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;


public class EpochServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        //try {
            Gson gson = new Gson();
            
            response.setStatus(HttpStatus.OK_200);
            //response.getWriter().println(gson.toJson(h.getData()));
        //} catch (IOException ex) {
        //    Logger.getLogger(EpochServlet.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }
}
