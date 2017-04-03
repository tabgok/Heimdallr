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
public class Payload {
    private String data;
    
    public Payload(String d){
        data = d;
    }
    
    public String toString(){
        return data;
    }
}
