/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author tbick
 */
public abstract class ResourceGenerator {
    private Object data = null;
    private TimerTask tTask = null;
    
    public ResourceGenerator(TimerTask t){
        tTask = t;
    }
    
    public void receiveData(Object data){
        this.data = data;
    }
    
    public void generateData(long msPeriod){
        if(tTask == null){
            System.out.println("Invalid timer task");
        }else if(msPeriod == 0){
            tTask.run();
        }else{
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(tTask, 0, msPeriod);
        }
    }
}
