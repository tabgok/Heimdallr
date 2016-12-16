/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import MessageCenter.MessageCenter;
import actor.Actor;

/**
 *
 * @author teague
 */
public class Main {
    public static void main(String args[]){
        MessageCenter.getMessageCenter().initialize();
        Actor a = new Actor();
        Actor b = new Actor();
    }
}
