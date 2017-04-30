package com.tabgok.data;


public final class LongData extends Data<Long>{
    private final Long value;
    
    public LongData(Long timestamp, String unit, String variable, Long value) {
        super(timestamp, unit, variable);
        this.value = value;
    }

    @Override
    public Long getData() {
        return value;
    }
}
