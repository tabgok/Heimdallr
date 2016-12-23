/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageCenter;

import java.util.Objects;

/**
 *
 * @author teague
 */
public class Address implements Comparable<Address> {
    private String address;
    private String globalAddress = "not implemented yet";
    private String localAddress = "not implemented yet";
    
   
    public Address(String addr){
        address = addr;
    }

    @Override
    public int compareTo(Address o) {
        return address.compareTo(o.address);
    }
    
    @Override
    public String toString(){
        return address;
    }
    
    public void setGlobalAddress(String s){
        globalAddress = s;
    }
    
    public void setLocalAddress(String s){
        localAddress = s;
    }
    
    public String getAddress(){
        return address;
    }
    
    @Override
    public boolean equals(Object o){
        return o != null && o instanceof Address && ((Address)o).getAddress().equals(address);
    }
    
    public boolean isDestinationCenter(Object o){
        return o!=null && o instanceof Address && ((Address)o).globalAddress.equals(globalAddress);
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(address, globalAddress, localAddress);
    }
}
