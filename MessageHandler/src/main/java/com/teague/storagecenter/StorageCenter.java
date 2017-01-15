/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teague.storagecenter;

import com.teague.actors.Actor;

/**
 *
 */
public interface StorageCenter extends Runnable{
    void setActor(Actor actor);
}
