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
public interface Sink {
    /**
     *
     * @param data
     * @return -1 on success, otherwise a number indicating the space in it's buffer
     */
    public long receiveData(Object data);
}
