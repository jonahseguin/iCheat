/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.checks;


import com.shawckz.icheat.ICheat;
import com.shawckz.icheat.check.CheckType;
import com.shawckz.icheat.check.TimerCheck;
import com.shawckz.icheat.profile.IProfile;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class CheckFastHeal extends TimerCheck {

    public CheckFastHeal() {
        super(CheckType.FAST_HEAL);
    }

    @EventHandler
    public void onHealth(EntityRegainHealthEvent e){
        if(e.getEntity() instanceof Player){
            Player pl = (Player) e.getEntity();
            if(e.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED) {
                IProfile p = ICheat.getInstance().getCache().getProfile(pl);
                p.getProfileData().setHealthPerSecond(p.getProfileData().getHealthPerSecond() + e.getAmount());
            }
        }
    }

    @Override
    public void check(IProfile player) {
        if(player.getProfileData().getHealthPerSecond() > 2){
            fail(player);
        }

        //Reset
        player.getProfileData().setHealthPerSecond(0);
    }

}
