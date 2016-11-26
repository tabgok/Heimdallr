/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tbick
 */
public class Harvester {

    private Integer value = 0;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Startup the back end system
        Harvester harvester = new Harvester();
        
        //Start gathering data
        harvester.periodicallyGenerateData(1000);
        
        //Setup the HTTP server
        
        try {
            InetSocketAddress is = new InetSocketAddress("localhost", 8888);
            HttpServer http = HttpServer.create(is, 100);
            
            http.createContext("/getMetric", new GetDataHandler(harvester));
            http.setExecutor(null);
            http.start();
            
        } catch (IOException ex) {
            Logger.getLogger(Harvester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("leaving main");
    }
    
    public Harvester(){
        System.out.println("A new harvester has been created");
    }
    
    public void generateData(){
        StringBuilder output = new StringBuilder();
        
        Process p;
        try{
            p = Runtime.getRuntime().exec("date +%s");
            p.waitFor();
            
            BufferedReader br = 
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            String line = "";
            
            while( (line = br.readLine()) != null){
                output.append(line);
            }
        }catch(Exception e){
            //TODO: Catch something better
            System.out.println("There was an error running the comand");
        }
        value = Integer.parseInt(output.toString());
        System.out.println(output.toString());
        System.out.println(this);
    }
    
    public void periodicallyGenerateData(long ms){
        RunSystemCall rsc = new RunSystemCall(this);
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(rsc, 0, ms);
    }
    
    public Object getData(){
        return value;
    }
}


class RunSystemCall extends TimerTask{
    private int counter = 0;
    private Harvester myHarvester = null;
    
    public RunSystemCall(Harvester h){
        myHarvester = h;
    }
    
    @Override
    public void run() {
        System.out.println("Counter: " + counter);
        counter++;
        myHarvester.generateData();
    }
}


class GetDataHandler implements HttpHandler {
    private Harvester myHarvester = null;
    
    public GetDataHandler(Harvester h){
        myHarvester = h;
    }
    
    @Override
    public void handle(HttpExchange he) throws IOException {
        String response = myHarvester.getData().toString();
        
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    
}
