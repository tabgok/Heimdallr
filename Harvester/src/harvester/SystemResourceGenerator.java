/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester;

import java.util.HashSet;
import java.util.Timer;

/**
 *
 * @author teague
 */
public class SystemResourceGenerator {
    private String systemCommand = null;
    private SystemResourceGeneratorTask myTask;
    private final HashSet<Sink> subscribers = new HashSet<>();
    private Object myData = null;
    
    public SystemResourceGenerator(String systemCommand){
        System.out.println("System Resource Generator Started");
        
        this.systemCommand = systemCommand;
        
        myTask = new SystemResourceGeneratorTask(this, systemCommand);
        
        Timer timer = new Timer();
        
        timer.scheduleAtFixedRate(myTask, 0, 1000);
         
    }
    
    public void subscribe(Sink sink) {
        subscribers.add(sink);
    }

    public Object getData() {
        return myData;
    }
    
    protected void publish(){
        for(Sink s: subscribers){
            s.receiveData(myData);
        }
    }
    
    protected void receiveData(Object data){
        myData = data;
        
        publish();
    }
}
