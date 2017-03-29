package com.tabgok.heimdallr;

import com.tabgok.entity.factory.MachineFactory;
import com.tabgok.server.Gateway;

/**
 * Starts up an instance of the Heimdallr system, which consists of two parts:
 *  1. Start a machine factory, which gathers all the information on the system and constructs an in-memory view of the local "Machine".
 *  2. Start up a "Gateway", which is an HttpServer, in order to respond to queries and provide JSON data
 */

public class HeimdallrServer {

    public static void main(String args[]){
        // Create an Http Server, with access to an in-memory model of the local "Machine"
        Gateway g = new Gateway(MachineFactory.getMachine());
        //Start running commands and building out the "Machine"
        MachineFactory.startUpdater();
    }
}
