/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemResourceGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import 

/**
 *
 * @author tbick
 */
public class SystemResourceGenerator {
    private String systemCommand = null;
    private SystemResourceGeneratorTask srgt;
    private static SystemResourceGenerator srg;
    private Object value;
    private long period;
    private String name = null;
    
    //This may changed later, currently using files for IPC
    private File outputFile = null;
    
    private SystemResourceGenerator(String name, String systemCommand, long period){
        this.systemCommand = systemCommand;
        this.period = period;
        this.name = name;
        
        initialize();
    }
    
    private void initialize(){
        //Create filepath and file
        if( !new File("./command_output").exists() ){
            new File("./command_output").mkdir();
        }
        
        outputFile = new File("./command_output/" + name);
        srgt = new SystemResourceGeneratorTask(this, systemCommand);
        
        Timer timer = new Timer();
        
        //Set the command to run periodically, or a single time.
        if(period  > 0){
            timer.scheduleAtFixedRate(srgt, 0, period);
        }else{
            timer.schedule(srgt, 0);
        }
    }
    
    protected void receiveData(Object value){
        this.value = value;
        System.out.println("Generated value: " + value);
        
        publish();
    }
    
    private void publish(){
        try {
            
            Files.write(outputFile.toPath(), Arrays.asList(value.toString()), Charset.forName("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(SystemResourceGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String args[]){
        if(args.length != 3){
            System.out.println("TODO: Usage");
            System.exit(-1);
        }
        
        srg = new SystemResourceGenerator(args[0], args[1], Long.parseLong(args[2]));
    }
}
