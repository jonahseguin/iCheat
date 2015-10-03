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
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CheckAutoClick extends TimerCheck {

    @ConfigData(value = "max-clicks-per-second")
    @Getter @Setter private int maxClicksPerSecond = 20;

    public CheckAutoClick() {
        super(CheckType.AUTO_CLICK);
    }

    @Override
    public void check(IProfile profile) {
        double[] cps = profile.getProfileData().getClicksPerSecond();

        if(cps[3] >= maxClicksPerSecond
                && cps[2] >= maxClicksPerSecond
                && cps[1] >= maxClicksPerSecond
                && cps[0] >= maxClicksPerSecond){
            fail(profile);
        }
        else if (closelyDifferent(cps, 4)){
            if(cps[3] >= maxClicksPerSecond){
                fail(profile);
            }
        }
        else if (getMeanAverage(cps) >= maxClicksPerSecond){
            fail(profile, "Average");
        }
        else if (cps[3] == cps[2] && cps[3] == cps[1] && cps[3] == cps[0]){
            fail(profile, "Consistent");
        }


        cps[0] = cps[1];
        cps[1] = cps[2];
        cps[2] = cps[3];
        cps[3] = 0.0;
        profile.getProfileData().setClicksPerSecond(cps);
    }

    private double getMeanAverage(double[] cps){
        return (cps[3] + cps[2] + cps[1] + cps[0]) / 4;
    }

    private boolean closelyDifferent(double[] cps, int diff){
        double a = cps[3];
        double b = cps[2];
        double c = cps[1];
        double d = cps[0];

        boolean ab = (a >= b ? a - b : b - a) <= diff;
        boolean ac = (a >= c ? a - c : c - a) <= diff;
        boolean ad = (a >= d ? a - d : d - a) <= diff;

        return ab && ac && ad;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onClick(PlayerInteractEvent e){
        Player pl = e.getPlayer();
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            IProfile p = ICheat.getInstance().getCache().getProfile(pl);
            updateCps(p,1);//Add 1 to their most recent CPS
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBreak(BlockBreakEvent e){
        if(e.isCancelled()) return;
        Player pl = e.getPlayer();
        IProfile p = ICheat.getInstance().getCache().getProfile(pl);
        updateCps(p, -0.5);
    }

    private void updateCps(IProfile p, double i){
        double[] cps = p.getProfileData().getClicksPerSecond();
        cps[3] = cps[3] + i;
        p.getProfileData().setClicksPerSecond(cps);
    }

}
