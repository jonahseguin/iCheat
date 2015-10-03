/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.check;

import org.apache.commons.lang.StringUtils;

/**
 * The CheckType enumerator
 * Used to finally represent a check's hack that is being prevented
 * or detected
 */
public enum CheckType {

    FLY,
    SPEED,
    VCLIP("VClip"),
    AUTO_CLICK("AutoClick"),
    TRIGGER_BOT("TriggerBot"),
    AURA,
    ANTI_KNOCKBACK("AntiKnockback"),
    FAST_BOW("FastBow"),
    CRITICALS,
    REGEN,
    NO_SLOW_DOWN("NoSlowDown"),
    AURA_TWITCH("AuraTwitch"),
    PHASE,
    BAD_PACKETS("BadPackets"),
    BED_FLY("BedFly"),
    ANGLE,
    REACH,
    NO_SWING,
    HEAD_ROLL("HeadRoll"),
    TAB_COMPLETE("TabComplete"),
    HIGH_JUMP("HighJump");

    private final String name;

    CheckType(String name){
        this.name = name;
    }
    CheckType() {
        this.name = StringUtils.capitalize(name().toLowerCase().replaceAll("_", ""));
    }

    public String getName() {
        return name;
    }
    @SuppressWarnings("all")
    @Override
    public String toString() {
        return getName();
    }

    public static CheckType fromString(String string){
        for(CheckType checkType : values()){
            if(checkType.getName().equalsIgnoreCase(string)){
                return checkType;
            }
        }
        return null;
    }

}