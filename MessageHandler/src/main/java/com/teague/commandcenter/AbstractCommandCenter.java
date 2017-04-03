/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.commandcenter;


import com.teague.actors.Actor;
import com.teague.messages.Message;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public abstract class AbstractCommandCenter implements CommandCenter{
    protected Actor actor;
    
    @Override
    public void run() {
        while(true){
            try {
                Message m = actor.getNextIncomingMessage();
                
                handleMessage(m);
            } catch (InterruptedException ex) {
                Logger.getLogger(PingCommandCenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    protected abstract void handleMessage(Message m);
    
    @Override
    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
