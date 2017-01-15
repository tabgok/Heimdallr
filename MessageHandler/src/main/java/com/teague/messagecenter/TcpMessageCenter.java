/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messagecenter;

import com.teague.messagecenter.messagesender.TcpMessageSender;
import com.teague.messagecenter.messagreceiver.TcpMessageReceiver;

/**
 *
 */
public class TcpMessageCenter extends AbstractMessageCenter {
    
    public TcpMessageCenter(){
        super(new TcpMessageReceiver(), new TcpMessageSender());
        System.out.println("Created a new tcp message center");
    }
}
