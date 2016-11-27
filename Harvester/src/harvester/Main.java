/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester;

import java.util.HashMap;

/**
 *
 * @author teague
 */
public class Main {
    public final HashMap<String, ResourceMonitor> resourceMonitors = new HashMap<>();
    private final static Main m = new Main();
    
    public static void main(String args[]){
        System.out.println("Starting program");
    }
    
    
    private Main(){
        ResourceMonitor rMonitor = new ResourceMonitor();
        
        resourceMonitors.put("Resources", rMonitor);
        
        DataServer ds = DataServer.getDataServer();
    }
    
    public static Main getMain(){
        return m;
    }
    
    
}
