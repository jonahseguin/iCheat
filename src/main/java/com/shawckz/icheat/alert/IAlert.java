/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.alert;

import com.shawckz.icheat.ICheat;
import com.shawckz.icheat.profile.IProfile;
import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Getter
public abstract class IAlert {

    public abstract boolean isApplicable(IProfile profile);

    public abstract void send(IProfile profile);

    /**
     * You should be calling this, not {@link IAlert#send(IProfile)} unless you
     * want to explicitly send to one player.
     */
    public void sendAlert(){
        for(Player pl : Bukkit.getOnlinePlayers()){
            IProfile profile = ICheat.getInstance().getCache().getProfile(pl);
            if(isApplicable(profile)){
                send(profile);
            }
        }
    }

}
