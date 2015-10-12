/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.profile.cache;

import com.shawckz.icheat.ISettings;
import com.shawckz.icheat.profile.IProfile;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ICache extends AbstractCache {

    public ICache(Plugin plugin) {
        super(plugin, IProfile.class);
    }

    public IProfile getProfile(String name){
        CachePlayer cachePlayer = getBasePlayer(name);
        if(cachePlayer != null && cachePlayer instanceof IProfile){
            return (IProfile) cachePlayer;
        }
        return null;
    }

    public IProfile getProfile(Player player){
        return getProfile(player.getName());
    }

    @Override
    public CachePlayer create(String name, String uuid) {
        return new IProfile(name, uuid);
    }

    @Override
    public void init(Player player, CachePlayer cachePlayer) {
        if(cachePlayer instanceof IProfile){
            IProfile profile = (IProfile) cachePlayer;
            profile.setBukkitPlayer(player);
            if(player.hasPermission(ISettings.PERMISSION_USE) && player.hasPermission("icheat.alerts")){
                profile.setAlertsEnabled(true);
                player.sendMessage(ISettings.PREFIX + ChatColor.translateAlternateColorCodes('&', " &7Alerts have been "
                        + (profile.isAlertsEnabled() ? "&aenabled" : "&cdisabled") + "&7."));
            }
        }
    }
}
