/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Harvester;

/**
 * Goal:
 *      Get a raw "time" property from a machine
 * 
 * How It'll be tested:
 *      1) Create a TCP server
 *      2) Create a harvester with parent (TCP.ip, TCP.port)
 *      3) Tell harvester to create datastream "ip.port.time" by running system command "gather time"
 *      4) Register to Harvester's "ip.port.time" datastream
 *      5) Tell harvester to gather once
 *      6) verify ip.port.time returns a reasonable value

 */
public class SystemHarvesterDateTest {
    public SystemHarvesterDateTest(){
        //
    }
}
