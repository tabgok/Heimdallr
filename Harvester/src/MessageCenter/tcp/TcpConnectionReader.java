/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageCenter.tcp;

import MessageCenter.Message;
import MessageCenter.MessageCenter;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author teague
 */
public class TcpConnectionReader implements Runnable {
    private final Scanner input;
    
    public TcpConnectionReader(Scanner input){
        this.input = input;
    }
    
    @Override
    public void run() {
        HashMap<String, String> messageData = new HashMap<>();
        
        
        while(input.hasNext()){
            String next = input.nextLine();
            if(next.contains(":")){
                String[] kv = next.split(" *: *");
                messageData.put(kv[0], kv[1]);
            }
        }
        
        if(messageData.containsKey("Destination") && 
                messageData.containsKey("Source") &&
                messageData.containsKey("Payload")){
            Message m = new Message(
                messageData.get("Destination"),
                messageData.get("Source"),
                messageData.get("Payload"));
                    
            MessageCenter.getMessageCenter().receiveMessage(m);
        }
        
        input.close();
    }
    
}
