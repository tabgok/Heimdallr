/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package actor;

import MessageCenter.Address;
import MessageCenter.Message;
import MessageCenter.MessageHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class SystemHarvester extends Actor implements AdvancedActor{
    private int data = -1;
    private final String command;
    private SystemCommandRunner runner;
    
    public SystemHarvester(String command){
        this.command = command;
    }

    @Override
    public void handleCommand(ActorCommand ac) {
        if(ac.command.equals("gather")){
            Thread tr = new Thread(new SystemCommandRunner(this, 0, command));
            
            tr.start();
            try {
                tr.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(SystemHarvester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

class SystemCommandRunner implements Runnable{
    private int period;
    private String command;
    private SystemHarvester sh;
    
    public SystemCommandRunner(SystemHarvester sh, int period, String command){
        this.period = period;
        this.command = command;
        this.sh = sh;
    }

    @Override
    public void run() {
        try {
            StringBuilder sb = new StringBuilder();
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader input = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));
            
            String line;
            while( (line = input.readLine()) != null){
                sb.append(line);sb.append("\n");
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(SystemCommandRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
