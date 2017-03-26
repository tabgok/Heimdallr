package com.tabgok.entity.factory;

import com.tabgok.data.LongData;
import com.tabgok.entity.MountedFilesystem;
import com.tabgok.entity.factory.harvester.Harvester;
import com.tabgok.entity.factory.harvester.HarvesterListener;
import com.tabgok.harvester.commands.CommandResult;
import java.util.HashMap;
import java.util.HashSet;


public class MountedFilesystemUpdater implements HarvesterListener {
    public static final HashMap<String, MountedFilesystem> mountedFilesystems = new HashMap<>();
    private final Harvester harvester;
    
    public MountedFilesystemUpdater(Harvester harvester){
        this.harvester = harvester;
    }
    
    public void startUpdater(){
        harvester.register(this, "MountedFilesystemUpdates");
    }
    
    @Override
    public void receive(CommandResult result) {
        if(result == null){ return; }
        
        switch(result.getVariable()){
            case "MountedFilesystemUpdates":
                updateFilesystems(result.getTimestamp(), result.getValue());
                break;
        }
    }
    
    private void updateFilesystems(long timestamp, String value){
        HashSet<String> filesystems = new HashSet<>();
        for(String line : value.split("\n")){
            if(line.contains("Filesystem")){
                continue;
            }
            
            String[] fields = line.split("\\s+");
            String mountPoint = fields[5];
            long numBlocks = Long.parseLong(fields[1]);
            long usedBlocks = Long.parseLong(fields[2]);
            filesystems.add(mountPoint);
            if(!mountedFilesystems.containsKey(mountPoint)){
                mountedFilesystems.put(mountPoint, new MountedFilesystem());
            }
           
            mountedFilesystems.get(mountPoint).setBlockCapacity(new LongData(timestamp, numBlocks));
            mountedFilesystems.get(mountPoint).setUsedBlocks(new LongData(timestamp, usedBlocks));
        }
        
        mountedFilesystems.keySet().retainAll(filesystems);
    }

}
