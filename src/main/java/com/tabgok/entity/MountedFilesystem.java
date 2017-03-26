package com.tabgok.entity;

import com.tabgok.data.StringData;
import com.tabgok.data.LongData;


public class MountedFilesystem extends Entity {
    private LongData inodeCapacity;
    private LongData blockCapacity;
    private LongData usedInodes;
    private LongData usedBlocks;
    private StringData mountPoint;
    
    public void setInodeCapacity(LongData capacity){
        inodeCapacity = capacity;
    }
    
    public void setBlockCapacity(LongData capacity){
        blockCapacity = capacity;
    }
    
    public void setUsedInodes(LongData used){
        usedInodes = used;
    }
    
    public void setUsedBlocks(LongData used){
        usedBlocks = used;
    }
    
    public void setMountpoint(StringData mountPoint){
        this.mountPoint = mountPoint;
    }
}
