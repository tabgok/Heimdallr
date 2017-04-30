package com.tabgok.data;


public abstract class Data<T> {
    private final Long timestamp;
    private final String unit;
    private final String variable;
    
    
    protected Data(Long timestamp, String unit, String variable){
        this.timestamp = timestamp;
        this.unit = unit;
        this.variable = variable;
    }
    
    public static IntegerData createIntegerData(Long timestamp, String unit, String variable, int value){
        return new IntegerData(timestamp, unit, variable, value);
    }
    
    public static LongData createLongData(Long timestamp, String unit, String variable, long value){
        return new LongData(timestamp, unit, variable, value);
    }
    
    public abstract T getData();
    
    public Long getTimestamp(){
        return timestamp;
    }
    
    public String getVariable(){
        return variable;
    }
    
    public String getUnit(){
        return unit;
    }
}
