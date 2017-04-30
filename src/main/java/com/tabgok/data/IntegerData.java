package com.tabgok.data;


public final class IntegerData extends Data<Integer>{
    private final Integer value;
    
    protected IntegerData(Long timestamp, String unit, String variable, int value){
        super(timestamp, unit, variable);
        this.value = value;
    }
    @Override
    public Integer getData() {
        return value;
    }

}
