package com.tabgok.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tabgok.entity.factory.MountedFilesystemUpdater;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;


public class FilesystemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String filesystemID = request.getParameter("id");
        String responseString = null;
        
        
        if(!MountedFilesystemUpdater.mountedFilesystems.containsKey(filesystemID)){
            response.setStatus(HttpStatus.NOT_FOUND_404);
            response.setContentType("text/plain;charset=utf-8");
            responseString = "Unable to find the specified filesystem: " + filesystemID + " =/";
        }else{
            responseString = gson.toJson(MountedFilesystemUpdater.mountedFilesystems.get(filesystemID));
            response.setStatus(HttpStatus.OK_200);
        }
            
        try {
            response.getWriter().println(responseString);
        } catch (IOException ex) {
            Logger.getLogger(MachineServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}