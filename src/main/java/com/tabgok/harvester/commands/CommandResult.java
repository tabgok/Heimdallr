package com.tabgok.harvester.commands;


public class CommandResult {
    final String variable;
    final String value;
    final String error;
    final Integer exitCode;
    final long timestamp;
    
    public CommandResult(String variable, String value, String error, Integer exitCode, long timestamp){
        this.variable = variable;
        this.value = value;
        this.error = error;
        this.exitCode = exitCode;
        this.timestamp = timestamp;
    }
    
    public String getVariable(){
        return variable;
    }
    
    public String getValue(){
        return value;
    }
    
    public long getTimestamp(){
        return timestamp;
    }
}
