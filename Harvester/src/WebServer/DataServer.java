/**
 * The DataServer connects to localhost on port 8888.
 * It essentially serves data from a file "cwd/command_output/epoch, which
 * is updated by another program.
 */
package WebServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tbick
 */
public class DataServer implements HttpHandler{
    private static DataServer ds = null;
    
    //All main needs to do is create a dataserver
    public static void main(String args[]){
        getDataServer();
    }
    
    //Only allow a single instance on a machine.
    public static DataServer getDataServer(){
        if(ds == null){
            ds = new DataServer();
        }
        
        return ds;
    }
    
    /**
     * Connect to localhost and listen on port 8888.
     * Register "/getMetric" to handler (this class) which
     * calls the "handle" subroutine.
     */
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
    
    /**
     * When called (i.e. when /getMetric is called), this function reads the
     * output of the file "$(cwd)/command_output/epoch and returns the data.
     * @param he
     * @throws IOException 
     */
    @Override
    public void handle(HttpExchange he) throws IOException {
        
        BufferedReader br = new BufferedReader(new FileReader("./command_output/epoch"));
        String response = br.readLine();
        System.out.println("Received a data request: " + response);
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
