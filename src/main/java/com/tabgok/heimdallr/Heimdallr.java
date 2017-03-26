package com.tabgok.heimdallr;

import com.tabgok.entity.factory.MachineFactory;
import com.tabgok.server.Gateway;


public class Heimdallr {
    /**
     * Starts up an instance of the Heimdallr system, which consists of two parts:
     *  1. Start a machine factory, which gathers all the information on the system and constructs an in-memory view of the local "Machine".
     *  2. Start up a "Gateway", which is an HttpServer, in order to respond to queries and provide JSON data
     * @param args No arguments expected
     */
    public static void main(String args[]){
        //Create the factory to create an in-memory "Machine" 
        MachineFactory factory = new MachineFactory();
        // Create the Http Server, with access to the in-memory "Machine"
        Gateway g = new Gateway(factory.getMachine());
        //Start running commands and building out the "Machine"
        factory.startUpdater();
    }
}
