/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actor;

import MessageCenter.Address;
import MessageCenter.Message;
import MessageCenter.MessageCenter;
import MessageCenter.MessageHandler;
import MessageCenter.Payload;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author teague
 */
public class Actor extends MessageHandler implements Runnable{
    private static int ACTORS = 1;
    private String name = "Actor" + (ACTORS++);
    
    public Actor(){
        System.out.println("Created actor: " + name);
        MessageCenter.getMessageCenter().registerRecipient(this);
        
        new Thread(this).start();
    }
    
    @Override
    public void handleMessage(Message m) {
        System.out.println(name+": Received a message:");
        System.out.println(m);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Actor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        m.setReply(new Payload(name+": received the message and is replying!"));
        this.sendReply(m);
    }
    
    @Override
    public void handleReply(Message m){
        System.out.println(name+": Received a reply: ");
        System.out.println(m);
    }
    
    @Override
    public Address getLocalAddress(){
        return new Address(name);
    }
}
