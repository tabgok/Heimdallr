/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package actor;

import MessageCenter.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class SystemCommandActor extends Actor {
    private int data = -1;
    private final int period;
    private final String command;
    private SystemCommandRunner runner;
    
    public SystemCommandActor(int period, String command){
        this.command = command;
        this.period = period;
    }
    
    public void handleMessage(Message m){
        
        runner = new SystemCommandRunner(period, command);
        
        new Thread(runner).start();
    }
    
    
}

class SystemCommandRunner implements Runnable{
    private int period;
    private String command;
    
    public SystemCommandRunner(int period, String command){
        this.period = period;
        this.command = command;
    }

    @Override
    public void run() {
        try {
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader input = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));
            
            String line = null;
            while( (line = input.readLine()) != null){
                System.out.println("Read line: " + line);
            }
        } catch (IOException ex) {
            Logger.getLogger(SystemCommandRunner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SystemCommandRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
