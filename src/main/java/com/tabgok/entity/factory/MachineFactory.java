package com.tabgok.entity.factory;

import com.tabgok.entity.harvester.Harvester;
import com.tabgok.entity.Machine;
import com.tabgok.data.StringData;
import com.tabgok.entity.harvester.HarvesterListener;
import com.tabgok.harvester.commands.CommandResult;
import com.tabgok.harvester.commands.SystemCommand;
import java.util.HashSet;


public class MachineFactory implements HarvesterListener{
    private static Machine machine;
    private Harvester harvester;
    private MountedFilesystemUpdater filesystemFactory;
    
    public MachineFactory(){}
   
    
    public Machine getMachine(){
        if(machine == null){
            machine = new Machine();
        }
        
        return machine;
    }
    
    public void startUpdater(){
        if(harvester == null){
            harvester = new Harvester();
            filesystemFactory = new MountedFilesystemUpdater(harvester);
            filesystemFactory.startUpdater();
            startMachineIDUpdates();
            startMountedFilesystemUpdates();
        }
    }
    
    private void startMachineIDUpdates(){
        SystemCommand findMachineID = new SystemCommand("MachineID", "cat /var/lib/dbus/machine-id");
        harvester.scheduleCommand(findMachineID, 10000);
        harvester.register(this, "MachineID");
    }

    private void startMountedFilesystemUpdates(){
        SystemCommand partitionCommand = new SystemCommand("MountedFilesystemUpdates", "df");
        harvester.scheduleCommand(partitionCommand, 1000);
        harvester.register(this, "MountedFilesystemUpdates");
    }
    
    @Override
    public void receive(CommandResult result) {        
        if(result == null){ return; }
        switch(result.getVariable()){
            
            case "MachineID":
                if(result.getValue() != null){
                    machine.setID(new StringData(result.getTimestamp(),result.getValue()));
                }
                break;
            
            case "MountedFilesystemUpdates":
                updateMountedFilesystemInformation(result.getValue());
                break;
        }
    }
    
    private void updateMountedFilesystemInformation(String value){
        HashSet<String> mountPoints = new HashSet<>();
        for(String line : value.split("\n")){
            if(line.contains("Filesystem")){
                continue;
            }
            
            String[] fields = line.split("\\s+");
            
            mountPoints.add(fields[5]);
        }
        machine.updateMountedFilesystemIDs(mountPoints);
    }
}
