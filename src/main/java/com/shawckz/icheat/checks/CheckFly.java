/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.checks;

import com.shawckz.icheat.ICheat;
import com.shawckz.icheat.check.Check;
import com.shawckz.icheat.check.CheckType;
import com.shawckz.icheat.configuration.annotations.ConfigData;
import com.shawckz.icheat.profile.IProfile;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class CheckFly extends Check {

    @ConfigData("max-air-time")
    private int maxAirTime = 15;

    public CheckFly() {
        super(CheckType.FLY);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        IProfile profile = ICheat.getInstance().getCache().getProfile(p);
        if(profile != null){
            profile.getProfileData().setLastGroundTime(System.currentTimeMillis());
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent e){
        if(e.isCancelled()) return;
        Player p = e.getPlayer();
        if(p.getAllowFlight() || p.getGameMode() == GameMode.CREATIVE || p.isFlying()) return;
        IProfile profile = ICheat.getInstance().getCache().getProfile(p);
        if(p.isOnGround() || p.getLocation().subtract(0,1,0).getBlock().getType() != Material.AIR){
            profile.getProfileData().setLastGroundTime(System.currentTimeMillis());
            return;
        }
        if(profile.getProfileData().getLastGroundTime() > 0){
            long ground = profile.getProfileData().getLastGroundTime();
            if(ground < System.currentTimeMillis()){
                if(e.getTo().getY() >= e.getFrom().getY()){
                    //They are not moving down (falling)
                    if(e.getTo().getY() >= 0){
                        //They are not in the void
                        int diff = (int) ((System.currentTimeMillis() - ground) / 1000);
                        if(diff >= maxAirTime){
                            //They have been in the air longer than the specified time
                            fail(profile);
                            profile.getProfileData().setLastGroundTime((System.currentTimeMillis()) - ((maxAirTime / 2)*1000));
                        }
                    }
                }
            }
        }
    }

}
