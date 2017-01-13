/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messagreceiver;

import com.teague.messages.Address;
import com.teague.messages.Message;

/**
 *
 */
public interface MessageReceiver {
    public Runnable receiveMessages();
    
    public Message getNextMessage();
    
    public Address getAddress();
}
