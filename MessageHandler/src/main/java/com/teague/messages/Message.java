/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messages;

/**
 *
 */
public class Message {
    private final Address sender;
    private final Address recepient;
    private final String message;
    
    public Message(Address sender, Address recepient, String message){
        this.sender = sender;
        this.recepient = recepient;
        this.message = message;
    }
    
    public Address getSender(){
        return sender;
    }
    
    public Address getRecepient(){
        return recepient;
    }
    
    public String getMessage(){
        return message;
    }
    
    @Override
    public String toString(){
        String msg = sender.getAddressString();
        msg += "\n"+recepient.getAddressString();
        msg += "\n" + message;
        
        return msg;
    }
}
