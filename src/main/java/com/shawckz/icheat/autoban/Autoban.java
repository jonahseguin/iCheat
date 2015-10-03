/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.autoban;

import com.shawckz.icheat.check.CheckType;
import lombok.Data;

@Data
public class Autoban {
    private final String name;
    private final CheckType checkType;
    private boolean cancelled;
}
