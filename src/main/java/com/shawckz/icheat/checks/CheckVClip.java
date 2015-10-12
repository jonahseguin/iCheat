/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.checks;

import com.shawckz.icheat.ICheat;
import com.shawckz.icheat.check.Check;
import com.shawckz.icheat.check.CheckType;
import com.shawckz.icheat.profile.IProfile;
import com.shawckz.icheat.util.Distance;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

public class CheckVClip extends Check {

    public CheckVClip() {
        super(CheckType.VCLIP);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerMoveEvent e){
        if(e.isCancelled()) return;
        if(e.getTo().getBlockX() == e.getFrom().getBlockX()
                && e.getTo().getBlockY() == e.getFrom().getBlockY()
                && e.getTo().getBlockZ() == e.getFrom().getBlockZ()){
            return;
        }
        Player pl = e.getPlayer();
        IProfile p = ICheat.getInstance().getCache().getProfile(pl);

        if(pl.getGameMode() == GameMode.CREATIVE) return;// Players with creative mode can usually glitch into blocks
        if(pl.getAllowFlight()) return;// Players with fly can also glitch into blocks.  Not necessary
        if(pl.isInsideVehicle() || pl.getVehicle() != null) return;

        Distance distance = new Distance(e.getFrom(),e.getTo());
        double to = Math.round(distance.toY());
        if(Math.round(distance.getYDifference()) < 2) return;// VClip requires at least 2 different, as vclip only works
                                                             // With at least a 2 block change

        for (int i = 0; i < (Math.round(distance.getYDifference())) + 1; i++) {
            Block block = new Location(pl.getWorld(), pl.getLocation().getX(), to + i, pl.getLocation().getZ()).getBlock();
            if (block.getType() != Material.AIR && block.getType().isSolid()){
                //They moved past a solid block, likely VCLIP.
                if(fail(p).isCancelled()){
                    e.setTo(e.getFrom());
                }
            }
        }
    }
}
