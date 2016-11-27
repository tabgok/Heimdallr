/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author teague
 */
public class DataServer implements HttpHandler{
    private static DataServer ds = null;
    
    public static DataServer getDataServer(){
        if(ds == null){
            ds = new DataServer();
        }
        
        return ds;
    }
    
    private DataServer(){
        try {
            InetSocketAddress is = new InetSocketAddress("localhost", 8888);
            HttpServer http = HttpServer.create(is, 100);
            
            http.createContext("/getMetric", this);
            http.setExecutor(null);
            http.start();
            System.out.println("Started a dataserver");
                    
        } catch (IOException ex) {
            Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void handle(HttpExchange he) throws IOException {
        String response = SystemResourceMonitor.getSystemResourceMonitor().getResourceGenerator("date").getData().toString();
        System.out.println("Received a data request: " + response);
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
