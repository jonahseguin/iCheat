/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.cmd.commands;

import com.shawckz.icheat.ICheat;
import com.shawckz.icheat.ISettings;
import com.shawckz.icheat.cmd.CmdArgs;
import com.shawckz.icheat.cmd.Command;
import com.shawckz.icheat.cmd.ICommand;
import com.shawckz.icheat.profile.IProfile;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@Command(name = "alerts", usage = "/alerts", playerOnly = true, permission = "icheat.alerts")
public class CommandAlerts implements ICommand {

    @Override
    public void onCommand(CmdArgs cmdArgs) {
        Player p = (Player) cmdArgs.getSender();
        IProfile ap = ICheat.getInstance().getCache().getProfile(p);
        if(cmdArgs.getArgs().length > 0 && p.hasPermission("icheat.alerts.others")){
            Player t = cmdArgs.getPlayer(0);
            if(t != null){
                IProfile at = ICheat.getInstance().getCache().getProfile(t);
                at.setAlertsEnabled(!at.isAlertsEnabled());
                t.sendMessage(ISettings.PREFIX + ChatColor.translateAlternateColorCodes('&'," &7Alerts have been " + (at.isAlertsEnabled() ?
                        "&aenabled" : "&cdisabled") + "&7."));
                p.sendMessage(ISettings.PREFIX + ChatColor.translateAlternateColorCodes('&'," &7Alerts have been "+(at.isAlertsEnabled() ? "&aenabled" : "&cdisabled")+
                        "&7 for "+t.getDisplayName()+"."));
            }
            else{
                p.sendMessage(ChatColor.RED+"Player '"+cmdArgs.getArg(0)+"' not found.");
            }
        }
        else{
            ap.setAlertsEnabled(!ap.isAlertsEnabled());
            p.sendMessage(ISettings.PREFIX + ChatColor.translateAlternateColorCodes('&'," &7Alerts have been "+(ap.isAlertsEnabled() ?
                    "&aenabled" : "&cdisabled")+"&7."));
        }
    }
}
