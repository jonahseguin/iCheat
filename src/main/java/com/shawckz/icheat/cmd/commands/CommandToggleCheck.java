/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.cmd.commands;

import com.shawckz.icheat.ICheat;
import com.shawckz.icheat.ISettings;
import com.shawckz.icheat.alert.alerts.IMessageAlert;
import com.shawckz.icheat.check.Check;
import com.shawckz.icheat.check.CheckManager;
import com.shawckz.icheat.cmd.CmdArgs;
import com.shawckz.icheat.cmd.Command;
import com.shawckz.icheat.cmd.ICommand;
import com.shawckz.icheat.profile.IProfile;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@Command(name = "togglecheck", usage = "/icheat togglecheck <check>", playerOnly = true, permission = "icheat.admin", minArgs = 1)
public class CommandToggleCheck implements ICommand {

    @Override
    public void onCommand(CmdArgs cmdArgs) {
        Player p = (Player) cmdArgs.getSender();
        IProfile profile = ICheat.getInstance().getCache().getProfile(p);
        if(cmdArgs.getArgs().length > 0){
            String checkName = cmdArgs.getArg(0);
            Check check = CheckManager.get().getCheck(checkName);
            if(check != null){
                check.setEnabled(!check.isEnabled());
                new IMessageAlert(ISettings.PREFIX+" The check "+ ChatColor.RED+check.getName()
                    +ChatColor.GRAY+" has been "+(check.isEnabled() ? ChatColor.GREEN+"enabled" : ChatColor.RED+"disabled")
                    +ChatColor.GRAY+".").sendAlert();
            }
            else{
                p.sendMessage(ChatColor.RED+"Check '"+checkName+"' does not exist.");
            }
        }
    }
}
