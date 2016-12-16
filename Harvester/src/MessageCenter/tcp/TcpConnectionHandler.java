/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageCenter.tcp;

import MessageCenter.MessageCenter;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author teague
 */
public class TcpConnectionHandler implements Runnable {
    private final Socket socket;
    private final Scanner socketReader;
    private final DataOutputStream socketWriter;
    
    public TcpConnectionHandler(Socket s) throws IOException{
        socket = s;
        socketReader = new Scanner(new BufferedReader(new InputStreamReader(socket.getInputStream())));
        socketWriter = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        TcpConnectionReader tcr = new TcpConnectionReader(socketReader);
        TcpConnectionWriter tcw = new TcpConnectionWriter(socketWriter);
        
        new Thread(tcr).start();
        new Thread(tcw).start();
        
    }
}
