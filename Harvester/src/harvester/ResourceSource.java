/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester;

import java.util.HashSet;

/**
 *
 * @author teague
 */
public class ResourceSource implements Source {
    private final HashSet<Sink> subscribers = new HashSet<>();
    
    public ResourceSource(){
        System.out.println("Created a new resource source");
    }
    
    @Override
    public void subscribe(Sink sink) {
        subscribers.add(sink);
    }

    @Override
    public Object getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected void publish(Object data){
        for(Sink s: subscribers){
            s.receiveData(data);
        }
    }
}
