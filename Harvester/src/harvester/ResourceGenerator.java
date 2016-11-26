/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester;

import java.util.TimerTask;

/**
 *
 * @author tbick
 */
public abstract class ResourceGenerator implements Generator, Source {
    private Object data = null;
    private TimerTask tTask = null;
    
    public ResourceGenerator(TimerTask t){
        tTask = t;
    }
    
    public void receiveData(Object data){
        this.data = data;
    }
    
    @Override
    public void generateData(long msPeriod){
        if(tTask == null){
            System.out.println("Invalid timer task");
        }else{
            
        }
    }
}
