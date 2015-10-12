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

public class CheckHeadRoll extends Check {

    public CheckHeadRoll() {
        super(CheckType.HEAD_ROLL);
        ICheat.getInstance().getProtocolManager().addPacketListener(new PacketAdapter(ICheat.getInstance(), ListenerPriority.HIGHEST,
                PacketType.Play.Client.LOOK) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if(!isEnabled()) return;
                if(event.isCancelled()) return;
                Player p = event.getPlayer();
                IProfile ap = ICheat.getInstance().getCache().getProfile(p);
                if (event.getPacketType() == PacketType.Play.Client.LOOK) {
                    float pitch = event.getPacket().getFloat().readSafely(1);
                    if (pitch > 90.1F || pitch < -90.1F) {
                        if(fail(ap).isCancelled()){
                            event.setCancelled(true);
                        }
                    }
                }
            }
        });
    }
}
