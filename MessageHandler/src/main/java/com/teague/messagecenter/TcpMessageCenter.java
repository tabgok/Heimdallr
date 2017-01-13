/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messagecenter;

import com.teague.messages.Message;
import com.teague.messagesender.MessageSender;
import com.teague.messagesender.TcpMessageSender;
import com.teague.messagreceiver.MessageReceiver;
import com.teague.messagreceiver.TcpMessageReceiver;

/**
 *
 */
public abstract class TcpMessageCenter extends MessageCenter {
    private final MessageSender sender;
    private final MessageReceiver receiver;
    
    public TcpMessageCenter(){
        receiver = new TcpMessageReceiver();
        sender = new TcpMessageSender();
        initialize();
    }
    
    @Override
    public MessageSender getMessageSender() {
        return sender;
    }

    @Override
    public MessageReceiver getMessageReceiver() {
        return receiver;
    }
    
    protected Message getNextMessage(){
        return receiver.getNextMessage();
    }
    
    protected void sendMessage(Message m){
        sender.send(m);
    }
}
