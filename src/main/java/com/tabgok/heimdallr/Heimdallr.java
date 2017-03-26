package com.tabgok.heimdallr;

import com.tabgok.entity.factory.MachineFactory;
import com.tabgok.server.Gateway;


public class Heimdallr {
    public static void main(String args[]){
        //Machine machine = new Machine();
        //EntityDatabase database = new PostgresConnector();
        
        MachineFactory factory = new MachineFactory();
        Gateway g = new Gateway(factory.getMachine());
        factory.startUpdater();
        
    }
}
