/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messagecenter.messagesender;

import com.teague.actors.Actor;
import com.teague.messages.Message;

/**
 *
 */
public interface MessageSender extends Runnable {
    void setActor(Actor a);
    void send(Message m);
}
