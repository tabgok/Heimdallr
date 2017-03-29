package com.tabgok.entity;

import com.tabgok.data.StringData;
import java.util.HashSet;
import java.util.Set;


public class Machine extends Entity {
    private StringData MachineID;
    private final HashSet<String> mountedFilesystems = new HashSet<>();
    
    public Machine(){}
    
    public StringData getID(){
        return MachineID;
    }
    
    public String[] getmountedFilesystemIDs(){
        return mountedFilesystems.toArray(new String[0]);
    }
    
    public void setID(StringData ID){
        this.MachineID = ID;
    }
    
    public void updateMountedFilesystemIDs(Set<String> IDs){
        mountedFilesystems.addAll(IDs);
        mountedFilesystems.retainAll(IDs);
    }
}
