/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messages;

/**
 *
 */
public class AddressFactory {
    private static final String TCP_TYPE = "1";
    public static Address getAddress(String addrString){
        String type = addrString.split(":")[0];
        
        Address addr = null;
        
        switch(type){
            case TCP_TYPE:
                String host = addrString.split(":")[1];
                Integer port = Integer.parseInt(addrString.split(":")[2]);
                addr = new TcpAddress(host, port);
                break;
            default:
                System.out.println("Unable to identify the address type: " + addrString);
        }
        
        return addr;
    }
    
    public static String getAddressString(Address addr){
        String addressString = null;
        if(addr instanceof TcpAddress){
            TcpAddress address = (TcpAddress)addr;
            
            addressString =  TCP_TYPE+":"+address.getHost()+":"+address.getPort();
        }
        
        return addressString;
    }
}
