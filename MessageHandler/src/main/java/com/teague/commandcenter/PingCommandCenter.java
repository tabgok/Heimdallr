/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.commandcenter;

import com.teague.messages.Message;

/**
 *
 */
public class PingCommandCenter extends AbstractCommandCenter {
    
    public PingCommandCenter(){
        System.out.println("Created a new PingCommandCenter");
    }
    @Override
    public void handleMessage(Message m) {
        if(m.getMessage().equals("Message:Ping")){
            System.out.println("Sending a pong reply");
            m.getReply(m, "Pong");
        }else{ 
            System.out.println("Unable to handle the message: " + m);
        }
    }
}
