/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.alert.alerts;

import com.shawckz.icheat.ISettings;
import com.shawckz.icheat.alert.IAlert;
import com.shawckz.icheat.check.Violation;
import com.shawckz.icheat.profile.IProfile;
import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;

public class IViolationAlert extends IAlert{

    private final Violation violation;
    private final String detail;


    public IViolationAlert(Violation violation) {
        this.violation = violation;
        this.detail = "";
    }

    public IViolationAlert(Violation violation, String detail){
        this.violation = violation;
        this.detail = detail;
    }

    @Override
    public boolean isApplicable(IProfile profile) {
        return profile.isAlertsEnabled();
    }

    @Override
    public void send(IProfile profile) {
        if(detail.equals("")){
            new FancyMessage(ISettings.PREFIX + " ")
                    .then(ChatColor.translateAlternateColorCodes('&',
                            profile.getBukkitPlayer().getDisplayName()+"&7(&c"+violation.getTotalVL()+"&7)&r &7failed &9"+
                                    violation.getCheck().getName()+"&7[&c"+violation.getVl()+"&7]"))
                    .send(profile.getBukkitPlayer());
        }
        else{
            new FancyMessage(ISettings.PREFIX + " ")
                    .then(ChatColor.translateAlternateColorCodes('&',
                            profile.getBukkitPlayer().getDisplayName()+"&7(&c"+violation.getTotalVL()+"&7)&r &7failed &9"+
                                    violation.getCheck().getName()+"&7(&c"+detail+"&7)[&c"+violation.getVl()+"&7]"))
                    .send(profile.getBukkitPlayer());
        }
    }
}
