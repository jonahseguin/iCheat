/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.checks;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.shawckz.icheat.ICheat;
import com.shawckz.icheat.check.Check;
import com.shawckz.icheat.check.CheckType;
import com.shawckz.icheat.profile.IProfile;

import org.bukkit.entity.Player;

public class CheckTabComplete extends Check {

    public CheckTabComplete() {
        super(CheckType.TAB_COMPLETE);

        ICheat.getInstance().getProtocolManager().addPacketListener(new PacketAdapter(ICheat.getInstance(), ListenerPriority.NORMAL,
                PacketType.Play.Client.TAB_COMPLETE) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if(!isEnabled()) return;
                Player p = event.getPlayer();
                IProfile profile = ICheat.getInstance().getCache().getProfile(p);
                if (event.getPacketType() == PacketType.Play.Client.TAB_COMPLETE) {
                    String s = event.getPacket().getStrings().read(0);
                    if (s.length() <= 2) return;
                    if(s.startsWith(".") && !s.startsWith("./")){
                        fail(profile, s);
                    }
                }
            }
        });
    }

}
