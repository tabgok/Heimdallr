package com.tabgok.harvester;

import com.tabgok.entity.Entity;
import com.tabgok.harvester.commands.Command;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class HarvestFactory implements Runnable {
    private static HarvestFactory factory;
    private final static HashMap<String, LinkedList<Entity>> listeners = new HashMap<>();
    private final static ScheduledExecutorService exec = Executors.newScheduledThreadPool(10);
    private HarvestFactory(){}
    
    public HarvestFactory getFactory(){
        if(factory == null){
            factory = new HarvestFactory();
        }
        
        return factory;
    }
    
    public static void runCommand(Command c, long timeoutMs){
        if(timeoutMs > 0){
            exec.scheduleAtFixedRate(c, timeoutMs, timeoutMs, TimeUnit.MILLISECONDS);
        }else{
            exec.schedule(c, 100, TimeUnit.MILLISECONDS);
        }
    }
    
    public void register(Entity e, String variable){
        if(!listeners.containsKey(variable)){
            listeners.put(variable, new LinkedList<>());
        }
        
        listeners.get(variable).add(e);
    }

    @Override
    public void run() {
        
    }
    
    public void receive(String variable, String value){
        for(Entity e : listeners.get(variable)){
            e.receive(value);
        }
    }
}
