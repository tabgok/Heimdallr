/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageCenter;

import MessageCenter.tcp.TcpServer;

/**
 *
 * @author teague
 */
public class MessageCenter {
    private final TcpServer tcpserver;
    private static final MessageCenter msgCtr = new MessageCenter();
    
    public static MessageCenter getMessageCenter(){
        return msgCtr;
    }
    
    private MessageCenter(){
        tcpserver = new TcpServer(this);
    }
    
    public void initialize(){
        new Thread(tcpserver).start();
    }
    
    public void publish(Message m){
        
    }
    
    public void requestReply(MessageCenterPath p){
        
    }
    
    public void subscribe(MessageCenterPath p){
        
    }
    
    public void register(MessageHandler mh){
        
    }
    
    public void receiveMessage(Message m){
        
    }
}
