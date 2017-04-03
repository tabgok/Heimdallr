/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.messages;

/**
 * 
 */
public abstract class Address {
    protected Address(Object... data){
        
    }
    
    public String getAddressString(){
        return AddressFactory.getAddressString(this);
    }
}
