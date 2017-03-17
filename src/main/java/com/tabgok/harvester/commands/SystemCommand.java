package com.tabgok.harvester.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SystemCommand extends Command {
    private final String systemCall;
    
    public SystemCommand(String variable, String systemCall){
        super(variable);
        this.systemCall = systemCall;
    }

    @Override
    protected final String runCommand() {
        String result = null;
        try {
            Process p = Runtime.getRuntime().exec(systemCall);
            p.waitFor();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            result = "";
            while ( (line=reader.readLine()) != null) {    
                result += line;
            }
        } catch (IOException ex) {
            Logger.getLogger(SystemCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SystemCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
