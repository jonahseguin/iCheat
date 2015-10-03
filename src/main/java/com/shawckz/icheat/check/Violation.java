/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.check;

import com.shawckz.icheat.profile.IProfile;
import lombok.Data;

import java.util.UUID;

@Data
public class Violation {

    private final IProfile profile;
    private final int totalVL;
    private final CheckType check;
    private final int vl;
    private final String id = UUID.randomUUID().toString().substring(0,6);
    private final boolean cancelled;

}
