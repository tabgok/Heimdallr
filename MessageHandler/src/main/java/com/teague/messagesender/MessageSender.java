/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messagesender;

import com.teague.messages.Message;

/**
 *
 */
public interface MessageSender {
    public Runnable sendMessages();
    
    public void send(Message m);
}
