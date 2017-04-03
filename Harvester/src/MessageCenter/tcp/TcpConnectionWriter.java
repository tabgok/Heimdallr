/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageCenter.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author teague
 */
public class TcpConnectionWriter implements Runnable {
    private final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(256);
    private final DataOutputStream output;
    
    public TcpConnectionWriter(DataOutputStream output){
        this.output = output;
    }
    
    public void write(String s){
        queue.add(s);
    }
    
    @Override
    public void run() {
        
        while(true){
            try {
                String s = queue.take();
                output.write(s.getBytes());
            } catch (IOException ex) {
                Logger.getLogger(TcpConnectionWriter.class.getName()).log(Level.SEVERE, null, ex);
                break;
            } catch (InterruptedException ex) {
                Logger.getLogger(TcpConnectionWriter.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
        
    }
    
}
