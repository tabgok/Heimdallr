package com.teague.heimdallr;

public class Data {
    private String unit;
    private String variable;
    private Object value;
    
    public Data(String unit, String variable, Object value){
        this.unit = unit;
        this.variable = variable;
        this.value = value;
    }
    
    public String getUnit(){
        return unit;
    }
    
    public String getVariable(){
        return variable;
    }
    
    public Object getValue(){
        return value;
    }
}
