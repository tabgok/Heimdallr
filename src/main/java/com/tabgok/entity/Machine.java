package com.tabgok.entity;

import com.tabgok.data.StringData;
import java.util.HashSet;
import java.util.Set;

/**
 * The machine is a model of a machine.  Importantly, the machine is the top-level
 * model for a machine, meaning it contains within it either references to, or values of,
 * everything within a machine from Hardware to Filesystems to applications.
 */
public class Machine extends Entity {
    private StringData MachineID;
    private final HashSet<String> mountedFilesystems = new HashSet<>();
    
    private Machine(){}
    
    public static Machine getMachine(){
        return new Machine();
    }
    
    /**
     * 
     * @return Returns the machine's ID - this ID is expected to be globally unique
     */
    public StringData getID(){
        return MachineID;
    }
    
    /**
     * 
     * @return Return the active mounted partition points on the machine
     */
    public String[] getmountedFilesystemIDs(){
        return mountedFilesystems.toArray(new String[0]);
    }
    
    /**
     * 
     * @param ID Set this machine's ID
     */
    public void setID(StringData ID){
        this.MachineID = ID;
    }
    
    /**
     * 
     * @param IDs Update this machine's mounted partitions
     */
    public void updateMountedFilesystemIDs(Set<String> IDs){
        mountedFilesystems.addAll(IDs);
        mountedFilesystems.retainAll(IDs);
    }
}
