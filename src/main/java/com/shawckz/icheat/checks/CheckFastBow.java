/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.checks;

import com.shawckz.icheat.ICheat;
import com.shawckz.icheat.check.Check;
import com.shawckz.icheat.check.CheckType;
import com.shawckz.icheat.profile.IProfile;
import com.shawckz.icheat.profile.IProfileData;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class CheckFastBow extends Check {

    public CheckFastBow(){
        super(CheckType.FAST_BOW);
    }

    @EventHandler
    public void onPull(PlayerInteractEvent e){
        Player pl = e.getPlayer();
        IProfile p = ICheat.getInstance().getCache().getProfile(pl);
        if(pl.getItemInHand() != null){
            if(pl.getItemInHand().getType() == Material.BOW){
                p.getProfileData().setBowPull(System.currentTimeMillis());
            }
        }
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent e){
        if(e.getEntity() instanceof Arrow){
            Arrow arrow = (Arrow) e.getEntity();
            if(arrow.getShooter() != null){
                if(arrow.getShooter() instanceof Player){
                    Player pl = (Player) arrow.getShooter();
                    IProfile p = ICheat.getInstance().getCache().getProfile(pl);
                    p.getProfileData().setBowShoot(System.currentTimeMillis());
                    p.getProfileData().setBowPower(e.getEntity().getVelocity().length());
                    if(check(p)){
                        e.setCancelled(true);
                    }
                    reset(p);
                }
            }
        }
    }

    private boolean check(IProfile p){
        IProfileData data = p.getProfileData();
        double pull = data.getBowShoot() - data.getBowPull();
        double power = data.getBowPower();

        if(power >= 2.5){
            if(pull <= 200){
                return fail(p).isCancelled();
            }
        }
        return false;
    }

    @EventHandler
    public void onSwap(PlayerItemHeldEvent e){
        Player pl = e.getPlayer();
        IProfile p = ICheat.getInstance().getCache().getProfile(pl);
        reset(p);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player pl = e.getPlayer();
        IProfile p = ICheat.getInstance().getCache().getProfile(pl);
        reset(p);
    }

    private void reset(IProfile p){
        p.getProfileData().setBowShoot(0);
        p.getProfileData().setBowPull(0);
        p.getProfileData().setBowPower(0);
    }

}
