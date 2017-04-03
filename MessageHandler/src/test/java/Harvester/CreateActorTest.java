/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Harvester;

import Utilities.TcpServer;
import Utilities.TestUtilities;

/**
 * Goal:
 *      Get a raw "time" property from a machine
 * 
 * How It'll be tested:
 *      1) Create a TCP server
 *      2) Create a generic actor with parent (TCP.ip, TCP.port)
 *      3) Gather (Actor.ip, Actor.port) response and ensure it is reasonable
 *      4) Send "ping" test to ("Actor.ip",Actor.port)
 *      5) Verify (Actor.IP, Actor.port) sends appropriate response to (TCP.ip, TCP.port);.

 */
public class CreateActorTest {
    TcpServer server = new TcpServer();
    
    
}
