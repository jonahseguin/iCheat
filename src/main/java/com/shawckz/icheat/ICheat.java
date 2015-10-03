/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.shawckz.icheat.autoban.AutobanManager;
import com.shawckz.icheat.profile.cache.ICache;

import org.bukkit.plugin.java.JavaPlugin;

public class ICheat extends JavaPlugin {

    private static ICheat instance;

    private ICache cache;
    private AutobanManager autobanManager;
    private ProtocolManager protocolManager;

    @Override
    public void onLoad(){
        protocolManager = ProtocolLibrary.getProtocolManager();
    }

    @Override
    public void onEnable(){
        instance = this;
        this.cache = new ICache(this);
        this.autobanManager = new AutobanManager(this);
    }

    @Override
    public void onDisable(){
        instance = null;
    }

    public static ICheat getInstance() {
        return instance;
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
