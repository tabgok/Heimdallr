package com.tabgok.entity;

import java.util.LinkedList;


public class Machine extends Entity {
    private final LinkedList<Disk> disks = new LinkedList<>();
    
    public Machine(){
         
   }

    @Override
    public void update(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
