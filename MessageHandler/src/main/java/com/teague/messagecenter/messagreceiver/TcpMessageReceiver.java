/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messagecenter.messagreceiver;

import com.teague.messages.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.teague.messages.Address;
import com.teague.messages.AddressFactory;
import com.teague.messages.TcpAddress;

/**
 *
 */
public class TcpMessageReceiver extends AbstractMessageReceiver{
    private ServerSocket serverSocket;

    public TcpMessageReceiver(){
        try {
            serverSocket = new ServerSocket(0);
            System.out.println("Listening on: " + serverSocket.getInetAddress()+":"+serverSocket.getLocalPort());
        } catch (IOException ex) {
           Logger.getLogger(TcpMessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        while(true){
            try {
                Socket newConnection = serverSocket.accept();

                Message m = getMessage(newConnection);
                if(m != null){
                    actor.addNextIncomingMessage(m);
                }
            } catch (IOException ex) {
                Logger.getLogger(TcpMessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

    @Override
    public void setAddress() {
        address = new TcpAddress(serverSocket.getInetAddress().toString(), serverSocket.getLocalPort());
    }
    
    public Message getMessage(Socket s) throws IOException{
        Message m = null;
        Scanner socketReader = new Scanner(new BufferedReader(new InputStreamReader(s.getInputStream())));

        try{
            Address sender = AddressFactory.getAddress(socketReader.nextLine());
            Address recepient = AddressFactory.getAddress(socketReader.nextLine());
            String message = socketReader.nextLine();


            m =  new Message(sender, recepient, message);
        } catch(Exception e){
            System.out.println("Invalid message");
        }
        socketReader.close();

        return m;
    }
}
