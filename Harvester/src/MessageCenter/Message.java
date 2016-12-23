/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageCenter;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Objects;

/**
 *
 * @author teague
 */
public class Message implements Comparable<Message>{
    public final String uniqueID = new BigInteger(130, new SecureRandom()).toString(32);
    private Address source;
    private Address destination;
    private Payload data;
    private Payload reply;
    
    private boolean isReply = false;
    private boolean requiresReply = false;
    private boolean requiresConfirmation = false;
    
    public void setConfirmationRequirements(boolean requires){
        requiresConfirmation = requires;
    }
    
    public void setReplyRequirements(boolean requires){
        requiresReply = requires;
    }
    
    public boolean requiresReply(){
        return requiresReply;
    }
    
    public Message(Address dst, Address src, Payload d){
        source = src;
        destination = dst;
        data = d;
    }
    
    public Message(){
        
    }
    
    public void setSource(Address src){
        source = src;
    }
    
    public void setDestination(Address dst){
        destination = dst;
    }
    
    public void setMessage(Payload data){
        this.data = data;
    }
    
    public String getMessage(){
        return data.toString();
    }
    
    public String getReply(){
        return reply.toString();
    }
    
    public void setReply(Payload reply){
        isReply = true;
        this.reply = reply;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("MESSAGE\n");
        sb.append("\tUniqueID: \t\t" + uniqueID +"\n");
        sb.append("\tSource: \t\t" + (source == null ? "N/A" : source) +"\n");
        sb.append("\tDestination: \t\t" + (destination == null ? "N/A" : destination)+"\n");
        sb.append("\tOriginal Message: \t" + (data == null ? "N/A" : data.toString())+"\n");
        sb.append("\tReply Message: \t\t" + (reply == null ? "N/A" : reply.toString())+"\n");
        
        return sb.toString();
    }
    
    public Address getDestination(){
        return destination;
    }
    
    public Address getSource(){
        return source;
    }
    
    public boolean isReply(){
        return isReply;
    }

    @Override
    public int compareTo(Message o) {
        return o.uniqueID.compareTo(uniqueID);
    }
    
    @Override
    public boolean equals(Object o){
        return o!=null && o instanceof Message && ((Message)o).uniqueID.equals(uniqueID);
    }
   
    @Override
    public int hashCode(){
        return Objects.hash(uniqueID);
    }
}
