/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.cmd.commands;

import com.shawckz.icheat.ICheat;
import com.shawckz.icheat.check.Check;
import com.shawckz.icheat.check.CheckManager;
import com.shawckz.icheat.cmd.CmdArgs;
import com.shawckz.icheat.cmd.Command;
import com.shawckz.icheat.cmd.ICommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@Command(name = "help", usage = "/icheat help", playerOnly = true, permission = "icheat.use")
public class CommandHelp implements ICommand {

    @Override
    public void onCommand(CmdArgs cmdArgs) {
        Player p = (Player) cmdArgs.getSender();
        //IProfile profile = ICheat.getInstance().getCache().getProfile(p);

        p.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "----------------------------------------------------");

        p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&7*** [&ciCheat Commands&7] ***"));

        for(ICommand command : ICheat.getInstance().getCommandHandler().getCommands()){
            if(command.getClass().isAnnotationPresent(Command.class)){
                Command cmd = command.getClass().getAnnotation(Command.class);
                p.sendMessage(ChatColor.GRAY+"/icheat "+cmd.name());
            }
        }

        p.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "----------------------------------------------------");

    }
}
