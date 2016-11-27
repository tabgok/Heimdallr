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
public class SystemResourceGeneratorTask extends TimerTask{
    private int counter = 0;
    private SystemResourceGenerator generator = null;
    private String systemCall = null;
    
    public SystemResourceGeneratorTask(SystemResourceGenerator generator, String systemCall){
        this.generator = generator;
        this.systemCall = systemCall;
        
        
        System.out.println("Created new SystemResourceGeneratorTask");
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
        
        //System.out.println(value);
        generator.receiveData(value);
    }
}
