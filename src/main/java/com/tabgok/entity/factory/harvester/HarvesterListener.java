package com.tabgok.entity.factory.harvester;

import com.tabgok.harvester.commands.CommandResult;



public interface HarvesterListener {
    void receive(CommandResult result);
}
