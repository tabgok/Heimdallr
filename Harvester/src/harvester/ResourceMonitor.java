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
public class ResourceMonitor {
    private final static ResourceMonitor RESOURCE_MONITOR = new ResourceMonitor();
    
    public static ResourceMonitor getResourceMonitor(){
        return RESOURCE_MONITOR;
    }
    
    private ResourceMonitor(){
        System.out.println("Resource monitor started");
        
        SystemResourceMonitor srm = SystemResourceMonitor.getSystemResourceMonitor();
    }
}
