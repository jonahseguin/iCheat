/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.alert.alerts;

import com.shawckz.icheat.alert.IAlert;
import com.shawckz.icheat.profile.IProfile;
import mkremins.fanciful.FancyMessage;

public class IMessageAlert extends IAlert{

    private final FancyMessage msg;

    public IMessageAlert(FancyMessage msg) {
        this.msg = msg;
    }

    public IMessageAlert(String msg){
        this.msg = new FancyMessage(msg);
    }

    @Override
    public boolean isApplicable(IProfile profile) {
        return profile.isAlertsEnabled();
    }

    @Override
    public void send(IProfile profile) {
        msg.send(profile.getBukkitPlayer());
    }
}
