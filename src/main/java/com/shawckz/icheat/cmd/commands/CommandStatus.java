/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.cmd.commands;

import com.shawckz.icheat.ICheat;
import com.shawckz.icheat.ISettings;
import com.shawckz.icheat.check.Check;
import com.shawckz.icheat.check.CheckManager;
import com.shawckz.icheat.cmd.CmdArgs;
import com.shawckz.icheat.cmd.Command;
import com.shawckz.icheat.cmd.ICommand;
import com.shawckz.icheat.profile.IProfile;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@Command(name = "status", usage = "/icheat status", playerOnly = true, permission = "icheat.status")
public class CommandStatus implements ICommand {

    @Override
    public void onCommand(CmdArgs cmdArgs) {
        Player p = (Player) cmdArgs.getSender();
        //IProfile profile = ICheat.getInstance().getCache().getProfile(p);

        p.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "----------------------------------------------------");

        p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&7*** [&ciCheat Status&7] ***"));

        String checkStatus = ChatColor.GRAY+"Checks: ";
        for(Check check : CheckManager.get().getChecks().values()){
            checkStatus += (check.isEnabled() ? ChatColor.GREEN+check.getName() : ChatColor.RED+check.getName())+ChatColor.GRAY+", ";
        }
        if(checkStatus.length() > 2){
            checkStatus = checkStatus.substring(0, checkStatus.length() - 2);
        }

        p.sendMessage(ChatColor.GRAY+"Version "+ChatColor.RED+ICheat.getInstance().getDescription().getVersion()+
                ChatColor.GRAY+" by "+ChatColor.RED+"Shawckz"+ChatColor.GRAY+".");

        p.sendMessage(checkStatus);

        p.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "----------------------------------------------------");

    }
}
