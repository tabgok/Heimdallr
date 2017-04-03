/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messagecenter;

import com.teague.actors.Actor;
import com.teague.messages.Address;

/**
 *
 */
public interface MessageCenter extends Runnable{
    //An actor is a player within this system - such as "Harvester 1", which may be responsible for gather information on one or more entities
    public Address getActorAddress(String actorName);
    
    //An entity is something physical which exists, such as a MACHINE or a DISK which is unique in the real world
    public Address getEntityAddress(String entityName);
    
    //The address for this message center (an actor address).  
    public Address getLocalAddress();

    void setActor(Actor a);
    
}
