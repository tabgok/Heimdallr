package com.tabgok.data;

import com.tabgok.data.Data;


public class LongData extends Data {
    private final long value;
    
    public LongData(long timestamp, long value){
        super(timestamp);
        this.value = value;
    }
}
