package com.tabgok.server;

import com.google.gson.Gson;
import com.tabgok.data.LongData;
import java.io.IOException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;


public class EpochServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        Gson gson = new Gson();

        response.setStatus(HttpStatus.OK_200);
        long time = Instant.now().getEpochSecond();
        LongData timeData = new LongData(time, time);
        try {
            response.getWriter().println(gson.toJson(timeData));
        } catch (IOException ex) {
            Logger.getLogger(EpochServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
}
