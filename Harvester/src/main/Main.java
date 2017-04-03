/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import MessageCenter.Message;
import MessageCenter.MessageCenter;
import MessageCenter.Payload;
import actor.Actor;

/**
 *
 * @author teague
 */
public class Main {
    public static void main(String args[]){
        MessageCenter msgctr = MessageCenter.getMessageCenter();
        msgctr.initialize();
        
        //Create our actor A as the first test
        Actor a = new Actor();
        
        //Create our actor B as the second test
        Actor b = new Actor();
        
        //Create the message to send to B, with return address Actor a.
        Message m = msgctr.newMessage();
        m.setMessage(new Payload("Hello!"));
        m.setSource(b.getLocalAddress());
        m.setDestination(a.getLocalAddress());
        
        System.out.println("Sending a message");
        //Send the message
        msgctr.send(m);
        
    }
    
    
    /**
     * To send a message
     * 
     * 1) Grab a new message (messagecenter adds in passed in handler as source)
     * 2) Put data into the message
     * 3) Put a return address on the message
     * 4) Specify a set of recipients
     * 5) Hand the message off to the message center
     * 
     * Each message:
     * - has a unique ID
     * 
     */
}
