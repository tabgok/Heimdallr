package com.tabgok.data;

import com.tabgok.data.Data;


public class StringData extends Data{
    private final String value;
    
    public StringData(long timestamp, String value) {
        super(timestamp);
        this.value = value;
    }

}
