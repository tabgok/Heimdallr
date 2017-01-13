/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messagesender;

import com.teague.messages.Message;
import com.teague.messages.TcpAddress;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class TcpMessageSender implements MessageSender {
    private LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    
    
    @Override
    public Runnable sendMessages() {
        return new Sender();
    }

    @Override
    public void send(Message m) {
        queue.offer(m);
    }

    private class Sender implements Runnable{

        @Override
        public void run() {
            while(true){
                try {
                    Message toSend = queue.take();
                    System.out.println("Going to send out a message: " + toSend);
                    
                    TcpAddress addr = (TcpAddress)toSend.getRecepient();
                    Socket socket = new Socket(addr.getHost(), addr.getPort());
                    
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                    output.writeBytes(toSend.toString());
                    socket.getOutputStream().flush();
                    socket.close();
                } catch (InterruptedException ex) {
                    Logger.getLogger(TcpMessageSender.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TcpMessageSender.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
}
