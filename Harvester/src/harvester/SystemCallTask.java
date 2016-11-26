/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TimerTask;

/**
 *
 * @author tbick
 */
public class SystemCallTask extends TimerTask{
    private int counter = 0;
    private ResourceGenerator generator = null;
    private String systemCall = null;
    
    public SystemCallTask(ResourceGenerator generator, String systemCall){
        this.generator = generator;
        this.systemCall = systemCall;
    }
    
    @Override
    public void run() {
        StringBuilder output = new StringBuilder();
        
        Process p;
        try{
            p = Runtime.getRuntime().exec(systemCall);
            p.waitFor();
            
            BufferedReader br = 
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            String line = "";
            
            while( (line = br.readLine()) != null){
                output.append(line);
            }
        }catch(IOException | InterruptedException e){
            System.out.println("There was an error running the comand");
        }
        String value = output.toString();
        
        generator.receiveData(value);
    }
}
