/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageCenter.tcp;

import MessageCenter.MessageCenter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author teague
 */
public class TcpServer implements Runnable {
    private ServerSocket serverSocket;
    
    public TcpServer(MessageCenter m){
        try {
            serverSocket = new ServerSocket(0);
            System.out.println("Listening on: " + serverSocket.getInetAddress()+":"+serverSocket.getLocalPort());
        } catch (IOException ex) {
            Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                Socket newConnection = serverSocket.accept();
                TcpConnectionHandler tch = new TcpConnectionHandler( newConnection);
                new Thread(tch).start();
            } catch (IOException ex) {
                Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
