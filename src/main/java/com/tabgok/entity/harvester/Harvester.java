package com.tabgok.entity.harvester;

import com.tabgok.harvester.commands.Command;
import com.tabgok.harvester.commands.CommandResult;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Harvester implements HarvesterListener {
    private final HashMap<String, HashSet<HarvesterListener>> listeners = new HashMap<>();
    private final ScheduledExecutorService exec = Executors.newScheduledThreadPool(10);
    
    public Harvester(){}
    
    public void scheduleCommand(Command c, long timeoutMs){
        if(timeoutMs > 0){
            c.setHarvester(this);
            exec.scheduleAtFixedRate(c, timeoutMs, timeoutMs, TimeUnit.MILLISECONDS);
            
        }else{
            exec.schedule(c, 100, TimeUnit.MILLISECONDS);
        }
        
    }
    
    public synchronized void register(HarvesterListener e, String variable){
        if(!listeners.containsKey(variable)){
            listeners.put(variable, new HashSet<>());
        }
        listeners.get(variable).add(e);
    }
    
    @Override
    public void receive(CommandResult result){
        if(result!=null){
            for(HarvesterListener e : listeners.get(result.getVariable())){
                e.receive(result);
            }
        }
    }
}
