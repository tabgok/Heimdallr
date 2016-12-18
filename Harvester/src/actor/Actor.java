/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actor;

import MessageCenter.Message;
import MessageCenter.MessageCenter;
import MessageCenter.MessageHandler;

/**
 *
 * @author teague
 */
public class Actor implements MessageHandler{
    private static int ACTORS = 1;
    private String name = "Actor " + (ACTORS++);
    
    public Actor(){
        System.out.println("Created actor: " + name);
        MessageCenter.getMessageCenter().registerHandler(name, this);
    }
    
    @Override
    public void handleMessage(Message m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
