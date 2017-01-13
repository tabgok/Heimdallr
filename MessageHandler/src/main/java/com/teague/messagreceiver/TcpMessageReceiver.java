/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messagreceiver;

import com.teague.messages.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.teague.messages.Address;
import com.teague.messages.AddressFactory;
import com.teague.messages.TcpAddress;

/**
 *
 */
public class TcpMessageReceiver implements MessageReceiver{
    private LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private ServerSocket serverSocket;
    private TcpAddress address = null;
    
    public TcpMessageReceiver(){
        try {
            serverSocket = new ServerSocket(0);
            System.out.println("Listening on: " + serverSocket.getInetAddress()+":"+serverSocket.getLocalPort());
            address = new TcpAddress(serverSocket.getInetAddress().toString(), serverSocket.getLocalPort());
        } catch (IOException ex) {
            Logger.getLogger(TcpMessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public Runnable receiveMessages() {
        System.out.println("Receiving messages");
        return new TcpServer(serverSocket);
    }

    @Override
    public Message getNextMessage() {
        try {
            return queue.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(TcpMessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public Address getAddress() {
        return this.address;
    }
    
    private class TcpServer implements Runnable{
        private ServerSocket serverSocket;
        
        public TcpServer(ServerSocket serverSocket){
            this.serverSocket = serverSocket;
        }
        
        @Override
        public void run() {
            while(true){
                try {
                    Socket newConnection = serverSocket.accept();

                    Message m = new TcpConnectionHandler(newConnection).getMessage();
                    System.out.println("Got a new message: "  + m);
                    if(m != null){
                        queue.offer(m);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(TcpMessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }
    
    private class TcpConnectionHandler {
        private Scanner socketReader;
        public TcpConnectionHandler(Socket s){
            try {
                socketReader = new Scanner(new BufferedReader(new InputStreamReader(s.getInputStream())));
            } catch (IOException ex) {
                Logger.getLogger(TcpMessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public Message getMessage(){
            Message m = null;
            
            try{
                Address sender = AddressFactory.getAddress(socketReader.nextLine());
                Address recepient = AddressFactory.getAddress(socketReader.nextLine());
                String message = socketReader.nextLine();
                
                
                m =  new Message(sender, recepient, message);
            } catch(Exception e){
                e.printStackTrace();
            }
            socketReader.close();
            
            return m;
        }
    }
}
