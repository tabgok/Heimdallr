/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageCenter;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author teague
 */
public abstract class MessageHandler implements Runnable{
    private final LinkedBlockingQueue<Message> incomingMessages = new LinkedBlockingQueue<>();
    private final static MessageCenter MESSAGE_CENTER = MessageCenter.getMessageCenter();
    
    public Message getNextMessage() throws InterruptedException{
        return incomingMessages.take();
    }
    
    protected void sendMessage(Message m){
        MESSAGE_CENTER.send(m);
    }
    public abstract void handleMessage(Message m);
    
    public abstract void handleReply(Message m);
    
    public abstract Address getLocalAddress();
    
    protected void enqueueMessage(Message m){
        incomingMessages.offer(m);
    }
    
    protected void sendReply(Message m){
        Address addr = m.getDestination();
        m.setDestination(m.getSource());
        m.setSource(addr);
        
        MESSAGE_CENTER.send(m);
    }
    
    @Override
    public void run(){
        try {
            Message m;
            
            while( (m=getNextMessage())!=null ){
                if(m.isReply()){
                    handleReply(m);
                }else{
                    handleMessage(m);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
