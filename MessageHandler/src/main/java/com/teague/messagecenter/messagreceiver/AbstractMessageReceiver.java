/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messagecenter.messagreceiver;

import com.teague.actors.Actor;
import com.teague.messages.Address;

/**
 *
 */
public abstract class AbstractMessageReceiver implements MessageReceiver {
    protected Actor actor;
    protected Address address;

    @Override
    public Address getAddress(){
        return address;
    }

    @Override
    public void setActor(Actor actor) {
        this.actor = actor;
    }
    
    protected abstract void setAddress();

}
