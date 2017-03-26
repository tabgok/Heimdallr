package com.tabgok.entity;

import com.tabgok.data.StringData;
import java.util.HashMap;
import java.util.Set;


public class Machine extends Entity {
    private StringData MachineID;
    private final HashMap<String, MountedFilesystem> mountedFilesystems = new HashMap<>();
    
    public Machine(){}
    
    public StringData getID(){
        return MachineID;
    }
    
    public String[] getmountedFilesystemIDs(){
        return mountedFilesystems.keySet().toArray(new String[0]);
    }
    
    public void setID(StringData ID){
        this.MachineID = ID;
    }
    
    public void updateMountedFilesystemIDs(Set<String> IDs){
        mountedFilesystems.keySet().addAll(IDs);
        mountedFilesystems.keySet().retainAll(IDs);
    }
}
