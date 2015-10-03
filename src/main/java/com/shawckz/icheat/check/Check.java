/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.check;

import com.shawckz.icheat.ICheat;
import com.shawckz.icheat.alert.alerts.IViolationAlert;
import com.shawckz.icheat.autoban.AutobanManager;
import com.shawckz.icheat.configuration.annotations.ConfigData;
import com.shawckz.icheat.profile.IProfile;
import lombok.Getter;
import lombok.Setter;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

/**
 * The Check class
 * Superclass to all Checks, provides base and other important stuff.
 */
@Getter
@Setter
public abstract class Check extends CheckConfig implements Listener {

    private final CheckType checkType;

    @ConfigData(value = "enabled") private boolean enabled = true;

    @ConfigData(value = "autoban") private boolean autoban = true;

    @ConfigData(value = "cancel") private boolean cancel = true;

    @ConfigData(value = "punish-level") private int punishLevel = 10;

    @ConfigData(value = "raise-level") private int raiseLevel = 2;

    @ConfigData(value = "punish-command") private String punishCommand = "";

    public Check(final CheckType checkType) {
        super(checkType);
        this.checkType = checkType;
    }

    public final void setEnabled(final boolean enabled) {
        if(this.enabled != enabled){
            if(enabled){
                Bukkit.getServer().getPluginManager().registerEvents(this, ICheat.getInstance());
                Bukkit.getLogger().info("[iCheat] Registered check "+getName());
            }
            else{
                HandlerList.unregisterAll(this);
            }
        }
        this.enabled = enabled;
    }

    protected final Violation fail(IProfile profile, String... detail){
        if(this.isCancel()){
            cancel(profile);
        }

        final Violation violation = profile.addVL(this.getCheckType(),this.isCancel());

        if(violation.getVl() > this.getPunishLevel() && this.isAutoban() && !ICheat.getInstance().getAutobanManager().hasAutoban(profile.getName())){
            //TODO: Autoban the player.
        }
        else{
            if(getDetail(violation) != null){
                // Send detailed alert
                new IViolationAlert(violation, getDetail(violation)).sendAlert();
            }
            else if (detail != null && detail.length > 0){
                new IViolationAlert(violation, detail[0]).sendAlert();
            }
            else{
                // Send normal alert
                new IViolationAlert(violation).sendAlert();
            }
        }

        return violation;
    }

    public void cancel(IProfile profile) {
        // Can be overridden
    }

    public final String getName(){
        return checkType.getName();
    }

    public String getDetail(Violation violation){
        // Can be overriden
        return null;
    }

}
