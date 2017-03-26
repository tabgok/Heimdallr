package com.tabgok.harvester.commands;

import com.tabgok.entity.factory.harvester.Harvester;

public abstract class Command implements Runnable {
    private final String variable;
    private Harvester harvester;
    
    public String getVariable(){
        return variable;
    }
    
    public Command(String variable){
        this.variable = variable;
    }
    
    @Override
    public void run() {
        CommandResult result = runCommand();
        
        if(result != null){
            harvester.receive(result);
        }
    }
    
    public void setHarvester(Harvester harvester){
        this.harvester = harvester;
    }
    
    protected abstract CommandResult runCommand();
}
