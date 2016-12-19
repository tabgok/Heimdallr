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
    private final TcpServer tcpserver;
    private static final MessageCenter msgCtr = new MessageCenter();
    private final HashMap<String, MessageHandler> handlers = new HashMap<>();
    
    public static MessageCenter getMessageCenter(){
        return msgCtr;
    }
    
    public void registerHandler(String id, MessageHandler mh){
        System.out.println("Registering a handler: '" + id +"'");
        handlers.put(id, mh);
    }
    
    public Message getMessage(Address destinationAddress){
        Message m = new Message();
        
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
        String dest = m.destination.address;

        if(handlers.containsKey(dest)){
            handlers.get(dest).handleMessage(m);
        }
    }
}
