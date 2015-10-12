/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.shawckz.icheat.autoban.AutobanManager;
import com.shawckz.icheat.check.CheckManager;
import com.shawckz.icheat.cmd.CommandHandler;
import com.shawckz.icheat.profile.cache.ICache;

import org.bukkit.plugin.java.JavaPlugin;

public class ICheat extends JavaPlugin {

    private static ICheat instance;

    private ICache cache;
    private AutobanManager autobanManager;
    private ProtocolManager protocolManager;
    private CommandHandler commandHandler;

    @Override
    public void onEnable(){
        instance = this;
        protocolManager = ProtocolLibrary.getProtocolManager();
        this.cache = new ICache(this);
        this.autobanManager = new AutobanManager(this);
        this.commandHandler = new CommandHandler(this);
        CheckManager.get().setupChecks();
    }

    @Override
    public void onDisable(){
        instance = null;
    }

    public static ICheat getInstance() {
        return instance;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public AutobanManager getAutobanManager() {
        return autobanManager;
    }

    public ICache getCache() {
        return cache;
    }
}
