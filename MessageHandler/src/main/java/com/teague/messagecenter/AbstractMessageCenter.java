/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messagecenter;

import com.teague.actors.Actor;
import com.teague.messagecenter.messagesender.AbstractMessageSender;
import com.teague.messagecenter.messagesender.MessageSender;
import com.teague.messagecenter.messagreceiver.AbstractMessageReceiver;
import com.teague.messagecenter.messagreceiver.MessageReceiver;
import com.teague.messages.Address;


public abstract class AbstractMessageCenter implements MessageCenter{
    protected MessageReceiver receiver;
    protected MessageSender sender;
    private Thread receiverThread;
    private Thread senderThread;
        
    protected Actor actor;
    
    @Override
    public Address getLocalAddress(){
        return receiver.getAddress();
    }
    
    public AbstractMessageCenter(AbstractMessageReceiver receiver, AbstractMessageSender sender){
        this.receiver = receiver;
        this.sender = sender;
        receiverThread = new Thread(receiver);
        senderThread = new Thread(sender);
    }
    
    @Override
    public void run(){
        receiverThread.start();
        senderThread.start();
    }

    @Override
    public Address getActorAddress(String actorName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Address getEntityAddress(String entityName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setActor(Actor a) {
        this.actor = a;
        receiver.setActor(actor);
        sender.setActor(actor);
    }
}
