/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messagecenter;

import com.teague.messagesender.MessageSender;
import com.teague.messagreceiver.MessageReceiver;
import com.teague.messages.Address;

/**
 *  One thread to receive   //Provided by a message center
 *  One thread to send      //Provided by a message center
 *  A main thread to handle.
 *      --
 */
public abstract class MessageCenter implements Runnable{
    private MessageReceiver receiver;
    private MessageSender sender;
    private Address addr;
    
    
    public Address getAddress(){
        return addr;
    }
    
    public MessageCenter(){
    }
    
    protected final void initialize(){
        sender = getMessageSender();
        receiver = getMessageReceiver();
        
        
        new Thread(sender.sendMessages()).start();
        
        new Thread(receiver.receiveMessages()).start();

        new Thread(this).start();

    }
    /*
     * These must be specified by the MessageCenter insantiation
     */
    public abstract MessageSender getMessageSender();
    public abstract MessageReceiver getMessageReceiver();    
}
