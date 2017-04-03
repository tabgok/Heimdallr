/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.storagecenter;

import com.teague.actors.Actor;

/**
 *
 */
public abstract class AbstractStorageCenter implements StorageCenter {
    Actor actor;
    
    public AbstractStorageCenter(){
        System.out.println("Created a new abstract storage center");
    }
    
    @Override
    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
