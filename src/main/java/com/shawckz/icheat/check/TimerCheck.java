/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.check;


import com.shawckz.icheat.profile.IProfile;

public abstract class TimerCheck extends Check {

    public TimerCheck(final CheckType checkType) {
        super(checkType);
    }

    public abstract void check(IProfile profile);//This method will be called ONCE every 20 ticks; or once a second.
                                                  //Used for checks such as speed that need to check something on a
                                                  //Per-second basis

}
