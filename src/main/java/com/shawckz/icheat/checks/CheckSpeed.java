/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.checks;

import com.shawckz.icheat.ICheat;
import com.shawckz.icheat.check.CheckType;
import com.shawckz.icheat.check.TimerCheck;
import com.shawckz.icheat.configuration.annotations.ConfigData;
import com.shawckz.icheat.profile.IProfile;
import lombok.Getter;
import lombok.Setter;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CheckSpeed extends TimerCheck {

    @ConfigData("max-blocks-per-second")
    @Getter @Setter private double maxBlocksPerSecond = 20;

    public CheckSpeed() {
        super(CheckType.SPEED);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onMove(PlayerMoveEvent e){
        if(e.isCancelled()) return;
        Player p = e.getPlayer();
        IProfile ap = ICheat.getInstance().getCache().getProfile(p);
        double distance = e.getTo().distance(e.getFrom());
        ap.getProfileData().setBlocksPerSecond(ap.getProfileData().getBlocksPerSecond() + distance);
    }

    @Override
    public final void check(IProfile player) {
        final Player p = player.getBukkitPlayer();
        if(p.isInsideVehicle()) return;// Riding a horse or minecart makes it possible to move much faster
        if(p.getAllowFlight()) return;
        //If they have both speed AND slowness, we're just going to ignore them...

        if(p.hasPotionEffect(PotionEffectType.SPEED) && !p.hasPotionEffect(PotionEffectType.SLOW)){
            PotionEffect potionEffect = null;

            for(PotionEffect effect : p.getActivePotionEffects()){
                if(effect.getType().equals(PotionEffectType.SPEED)){
                    potionEffect = effect;
                    break;
                }
            }

            double maxBps = this.maxBlocksPerSecond;

            if(potionEffect != null){
                //They have speed
                int amp = potionEffect.getAmplifier();
                maxBps = maxBlocksPerSecond + (maxBlocksPerSecond*((amp + 1)*0.20));// Speed increases by 20%
            }

            double bps = player.getProfileData().getBlocksPerSecond();

            if(bps > maxBps){
                fail(player);
            }

        }
        else if (p.hasPotionEffect(PotionEffectType.SLOW) && !p.hasPotionEffect(PotionEffectType.SPEED)){
            //Slowness decreases by 15% * potion level
            PotionEffect potionEffect = null;
            for(PotionEffect effect : p.getActivePotionEffects()){
                if(effect.getType().equals(PotionEffectType.SLOW)){
                    potionEffect = effect;
                    break;
                }
            }

            double maxBps = this.maxBlocksPerSecond;

            if(potionEffect != null){
                //They have slow
                int amp = potionEffect.getAmplifier();
                maxBps = maxBlocksPerSecond * (amp - (amp * 0.15));// Slow decreases by 15%
            }

            double bps = player.getProfileData().getBlocksPerSecond();

            if(bps > maxBps){
                fail(player);
            }
        }
        else if (!p.hasPotionEffect(PotionEffectType.SPEED) && !p.hasPotionEffect(PotionEffectType.SLOW)){

            double bps = player.getProfileData().getBlocksPerSecond();

            if(bps > this.maxBlocksPerSecond){
                fail(player);
            }
        }

        //Reset their blocks per second
        player.getProfileData().setBlocksPerSecond(0);
    }

    @Override
    public final void cancel(IProfile player) {
        player.getBukkitPlayer().teleport(player.getBukkitPlayer().getLocation());
    }

}
