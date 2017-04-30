package com.tabgok.harvesters;

import com.tabgok.data.Data;


public class CurrentTimeHarvester implements Harvester {
    private String unit = "millisecond";
    private String variable = "CurrentTime";
    
    @Override
    public Data gatherData(){
        long time = System.currentTimeMillis();
        return Data.createLongData(time, unit, variable, time);
    }
}
