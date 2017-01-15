/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.commandcenter;

import com.teague.actors.Actor;

/**
 *
 */
public interface CommandCenter extends Runnable{
    void setActor(Actor actor);
}
