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
public interface MessageReceiver extends Runnable{
    public Address getAddress();
    void setActor(Actor actor);
}
