package com.tabgok.heimdallr;

import com.tabgok.entity.updater.MachineUpdater;
import com.tabgok.entity.Machine;
import com.tabgok.server.Gateway;

/**
 * Starts up a HiemdallrServer, which gathers information on the local machine 
 * and makes it available via an HttpServer.
 */

public class HeimdallrServer {

    public static void main(String args[]){
        Machine machine = Machine.getMachine();
        MachineUpdater updater = MachineUpdater.getUpdater(machine);
        Gateway gateway = Gateway.getGateway(machine);
        
        //Start the job scheduler to update the Machine model
        updater.start();
        //Start the Http Gateway
        gateway.start();
    }
}
