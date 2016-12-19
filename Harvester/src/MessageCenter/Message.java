/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageCenter;

/**
 *
 * @author teague
 */
public class Message {
    protected Address source;
    protected Address destination;
    protected Payload data;
    
    public Message(String dst, String src, String d){
        source = new Address(src);
        destination = new Address(dst);
        data = new Payload(d);
    }
    
    public Message(){
        
    }
    
    
    public String getRequest(){
        return data.toString();
    }
    
    public void reply(){
        
    }
}
