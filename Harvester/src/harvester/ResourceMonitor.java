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
    public HashMap<String, SystemResourceMonitor> systemMonitors = new HashMap<>();
    
    public ResourceMonitor(){
        System.out.println("Resource monitor started");
        
        SystemResourceMonitor srm = new SystemResourceMonitor();
        systemMonitors.put("system", srm);
    }
}
