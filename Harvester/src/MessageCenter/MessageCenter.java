/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageCenter;

import MessageCenter.tcp.TcpServer;
import java.util.HashMap;

/**
 *
 * @author teague
 */
public class MessageCenter {
    private Address myAddress = new Address("not implemented yet");
    private final TcpServer tcpserver;
    private static final MessageCenter msgCtr = new MessageCenter();
    private final HashMap<Address, MessageHandler> handlers = new HashMap<>();
    
    public static MessageCenter getMessageCenter(){
        return msgCtr;
    }
    
    public void registerRecipient(MessageHandler mh){
        if(handlers.containsKey(mh.getLocalAddress())){
            System.out.println("A handler is trying to register under an already used name");
            System.exit(1);
        }
        
        System.out.println("Registering address: " + mh.getLocalAddress());
        handlers.put(mh.getLocalAddress(), mh);
    }
    
    
    public Message newMessage(){
        return new Message();
    }
    
    public Message newMessage(MessageHandler source){
        Message m = new Message();
        m.setSource(source.getLocalAddress());
        
        return m;
    }
    
    public Message newMessage(MessageHandler source, Address destination){
        Message m = new Message();
        m.setSource(source.getLocalAddress());
        m.setDestination(destination);
        
        return m;
    }
    
    public Message newMessage(Address source, Address destination){
        Message m = new Message();
        m.setSource(source);
        m.setDestination(destination);
        
        return m;
    }
    private MessageCenter(){
        tcpserver = new TcpServer(this);
    }
    
    public void initialize(){
        new Thread(tcpserver).start();
    }
    
    public void publish(Message m){
        
    }
    
    public void requestReply(Address p){
        
    }
    
    public void subscribe(Address p){
        
    }
    
    public void register(MessageHandler mh){
        
    }
    
    public void receiveMessage(Message m){
        String dest = m.getDestination().toString();

        if(handlers.containsKey(dest)){
            handlers.get(dest).enqueueMessage(m);
        }
    }
    
    public void send(Message m){
        System.out.println("Attempting to send message: \n");
        System.out.println(m);
        //If the message is local, just send to the local Message Handler
        if(myAddress.isDestinationCenter(m.getDestination())){
            System.out.println("I am the destination center, looking for local message handler");
            if(handlers.containsKey(m.getDestination())){
                System.out.println("Found a registered destination!");
                handlers.get(m.getDestination()).enqueueMessage(m);
                //TODO: Warning, with this setup we run the risk of stalling everything due to
                //      a bad implementation of a message handler.
            }else{
                System.out.println("I was unable to find the destination");
            }
        }else{
            System.out.println("I am not the destination center, looking for remote message handler");
        }
    }
}
