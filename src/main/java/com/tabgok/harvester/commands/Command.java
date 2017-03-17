package com.tabgok.harvester.commands;

import com.tabgok.harvester.HarvestFactory;

public abstract class Command implements Runnable {
    private final String variable;
    
    public Command(String variable){
        this.variable = variable;
    }
    
    @Override
    public void run() {
        String result = runCommand();
        HarvestFactory.receive(variable, result);
    }
    
    
    protected abstract String runCommand();
}
