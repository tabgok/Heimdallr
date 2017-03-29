package com.tabgok.harvester.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SystemCommand extends Command {
    private final String systemCall;
    private final String variable;
    
    public SystemCommand(String variable, String systemCall){
        super(variable);
        this.systemCall = systemCall;
        this.variable = variable;
    }

    @Override
    protected final CommandResult runCommand() {
        CommandResult result = null;
        try {
            Process p = Runtime.getRuntime().exec((new String[] {"/bin/bash", "-c", systemCall}));
            p.waitFor();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            String value = "";
            while ( (line=reader.readLine()) != null) {    
                value += line+"\n";
            }
            
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String err = "";
            while( (line = error.readLine()) != null){
                err += line+"\n";
            }
            
            result = new CommandResult(variable, value, err, p.exitValue(), Instant.now().getEpochSecond());
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(SystemCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
