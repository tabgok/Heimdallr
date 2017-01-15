/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messagecenter.messagesender;

import com.teague.messages.Message;
import com.teague.messages.TcpAddress;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class TcpMessageSender extends AbstractMessageSender {
    @Override
    public void send(Message toSend) {
        try {
            TcpAddress addr = (TcpAddress)toSend.getRecepient();
            Socket socket = new Socket(addr.getHost(), addr.getPort());
            
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeBytes(toSend.toString());
            socket.getOutputStream().flush();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(TcpMessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
