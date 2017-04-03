/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.actors;

import com.teague.commandcenter.CommandCenter;
import com.teague.messagecenter.MessageCenter;
import com.teague.messages.Address;
import com.teague.messages.Message;
import com.teague.storagecenter.StorageCenter;

/**
 *
 */
public interface Actor {
    Message getNextOutgoingMessage() throws InterruptedException;
    void addNextOutgoingMessage(Message m);
    
    Message getNextIncomingMessage() throws InterruptedException;
    void addNextIncomingMessage(Message m);
    
    void start();
    void stop();
    
    void setMessageCenter(MessageCenter mc);
    void setStorageCenter(StorageCenter sc);
    void setCommandCenter(CommandCenter cc);
    void setParentAddress(Address parentAddr);
}
