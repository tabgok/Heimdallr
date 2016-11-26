/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester;

/**
 *
 * @author tbick
 */
public interface Source {
    /**
     * Subscribes to the data in this sink.
     * @param sink 
     */
    public void subscribe(Sink sink);
    
    /**
     * Retrieves the latest (perhaps set of) data from this source.
     * @return 
     */
    public Object getData();
}
