/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messagecenter.messagesender;

import com.teague.actors.Actor;
import com.teague.messages.Message;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public abstract class AbstractMessageSender implements MessageSender{
    protected Actor actor;
    
    @Override
    public void run() {
        while(true){
            try {
                Message toSend = actor.getNextOutgoingMessage();
                send(toSend);
            } catch (InterruptedException ex) {
                Logger.getLogger(TcpMessageSender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void setActor(Actor actor) {
        this.actor = actor;
    }

}
