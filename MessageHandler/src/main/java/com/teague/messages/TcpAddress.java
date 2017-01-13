/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messages;

/**
 *
 */
public class TcpAddress extends Address {
    private String host;
    private Integer port;
    
    
    public TcpAddress(String host, Integer port){
        this.host = host;
        this.port = port;
    }
    
    public String getHost(){
        return host;
    }
    
    public Integer getPort(){
        return port;
    }
}
