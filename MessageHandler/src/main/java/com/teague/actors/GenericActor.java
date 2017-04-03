/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.actors;

import com.teague.commandcenter.CommandCenter;
import com.teague.commandcenter.PingCommandCenter;
import com.teague.messagecenter.MessageCenter;
import com.teague.messagecenter.TcpMessageCenter;
import com.teague.messages.Address;
import com.teague.messages.AddressFactory;
import com.teague.messages.Message;
import com.teague.processcenter.ProcessCenter;
import com.teague.storagecenter.SqlStorageCenter;
import com.teague.storagecenter.StorageCenter;
import java.util.concurrent.LinkedBlockingQueue;


public class GenericActor implements Actor {
    private MessageCenter messageCenter;    // For sending and receiving messages
    private StorageCenter storageCenter;    // For storing data (both locally and externally)
    private CommandCenter commandCenter;    // For handling incoming commands and sending out commands
    private ProcessCenter processCenter;    // For harvesters and aggregators - what to do with data
    
    private Thread messageThread;
    private Thread storageThread;
    private Thread commandThread;
    
    private final LinkedBlockingQueue<Message> inputQueue = new LinkedBlockingQueue<>();
    private final LinkedBlockingQueue<Message> outputQueue = new LinkedBlockingQueue<>();
    
    protected GenericActor(){
        
    }
 
    @Override
    public void start(){
        messageThread = new Thread(messageCenter);
        storageThread = new Thread(storageCenter);
        commandThread = new Thread(commandCenter);
        
        commandThread.start();
        storageThread.start();
        messageThread.start();
    }
    
    @Override
    public Message getNextIncomingMessage() throws InterruptedException {
        return inputQueue.take();
    }
   
    @Override
    public Message getNextOutgoingMessage() throws InterruptedException{
        return outputQueue.take();
    }

    @Override
    public void addNextOutgoingMessage(Message m) {
        outputQueue.offer(m);
    }

    @Override
    public void addNextIncomingMessage(Message m) {
        inputQueue.offer(m);
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMessageCenter(MessageCenter mc) {
        this.messageCenter = mc;
        mc.setActor(this);
        this.messageThread = new Thread(mc);
    }

    @Override
    public void setStorageCenter(StorageCenter sc) {
        this.storageCenter = sc;
        sc.setActor(this);
        this.storageThread = new Thread(sc);
    }

    @Override
    public void setCommandCenter(CommandCenter cc) {
        this.commandCenter = cc;
        cc.setActor(this);
        this.commandThread = new Thread(cc);
    }
    
    @Override
    public void setParentAddress(Address parentAddr) {
        if(parentAddr != null){
            Message parentNotification = new Message(messageCenter.getLocalAddress(), parentAddr, "ChildCreated:"+messageCenter.getLocalAddress());
            addNextOutgoingMessage(parentNotification);
        }
    }
    
    public static void main(String args[]){
        if(args.length!=3 || args.length != 4){
            System.out.println("Invalid number of inputs");
            return;
        }
        String messageCenterType = args[0];
        String storageCenterType = args[1];
        String commandCenterType = args[2];
        
        MessageCenter msgCtr = null;
        StorageCenter stgCtr = null;
        CommandCenter cmdCtr = null;
        
        switch(messageCenterType){
            case "TCP":
                msgCtr = new TcpMessageCenter();
                break;
            default:
        }
        
        switch(storageCenterType){
            case "SQL":
                stgCtr = new SqlStorageCenter();
                break;
            default:
        }
        
        switch(commandCenterType){
            case "PING":
                cmdCtr = new PingCommandCenter();
                break;
            default:
        }
        
        if(msgCtr == null || stgCtr == null || cmdCtr == null){
            System.out.println("Invalid inputs");
            return;
        }
        
        GenericActor ga = new GenericActor();
        ga.setCommandCenter(cmdCtr);
        ga.setMessageCenter(msgCtr);
        ga.setStorageCenter(stgCtr);
        ga.start();
        
        if(args.length == 4){
            ga.setParentAddress(AddressFactory.getAddress(args[3]));
        }
    }


}
