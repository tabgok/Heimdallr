/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package messagehandlers;

import com.teague.messages.Message;
import com.teague.messagecenter.TcpMessageCenter;
import com.teague.messages.Address;
import com.teague.messages.AddressFactory;
import com.teague.messages.TcpAddress;

/**
 *
 */
public class Harvester extends TcpMessageCenter {
    private static Integer GLOBALID = 1;
    private Integer ID = GLOBALID++;
    public Harvester(){
        TcpAddress indexerAddress = new TcpAddress("localhost", 8888);
        Message m = new Message(getAddress(), indexerAddress, "Register me");
        sendMessage(m);
    }
    
    @Override
    public void run() {
        while( true ){
            Message m = getNextMessage();
            
            System.out.println("Harvester (" + ID + ") received a new message: " + m);
            Address sender = m.getSender();
            Address receiver = m.getRecepient();
            String message = m.getMessage();
            
            //Just a funky thing for testing
            receiver = AddressFactory.getAddress(m.getMessage());
            
            if(receiver != null){
                Message reply = new Message(sender, receiver, "Thanks for the message");
                sendMessage(reply);
            }
        }
    }
}


