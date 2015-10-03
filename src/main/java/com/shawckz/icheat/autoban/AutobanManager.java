/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.autoban;

import com.shawckz.icheat.ICheat;

import java.util.HashMap;
import java.util.Map;

public class AutobanManager {

    private final Map<String, Autoban> autobans = new HashMap<>();

    public AutobanManager(ICheat instance) {
    }

    public Autoban getAutoban(String name){
        return autobans.get(name);
    }

    public  boolean hasAutoban(String name) {
        return autobans.containsKey(name) && !autobans.get(name).isCancelled();
    }

    public void putAutoban(Autoban autoban){
        //if(!hasAutoban(autoban.getName())){
        autobans.put(autoban.getName(),autoban);
        //}
    }
}
