/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.profile;

import com.shawckz.icheat.check.Check;
import com.shawckz.icheat.check.CheckManager;
import com.shawckz.icheat.check.CheckType;
import com.shawckz.icheat.check.Violation;
import com.shawckz.icheat.profile.cache.CachePlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

@Getter
@Setter
@RequiredArgsConstructor
public class IProfile extends CachePlayer {

    private final String name;
    private final String uniqueId;
    private int totalVL = 0;

    private Player bukkitPlayer;
    private boolean alertsEnabled = false;
    private boolean alertTP = false;

    private final Map<CheckType, Integer> violationLevels = new HashMap<>();
    private final Map<String, Violation> violations = new HashMap<>();
    private final IProfileData profileData = new IProfileData();

    public Violation addVL(CheckType checkType, boolean cancelled){
        Check check = CheckManager.get().getCheck(checkType);
        if (check == null) {
            throw new NullPointerException("Check can't be null");
        }
        totalVL += check.getRaiseLevel();
        if(!violationLevels.containsKey(checkType)){
            violationLevels.put(checkType,0);
        }
        violationLevels.put(checkType,violationLevels.get(checkType)+check.getRaiseLevel());

        final Violation violation = new Violation(this, this.getTotalVL(), checkType,
                this.getVL(checkType),cancelled);

        violations.put(violation.getId(), violation);

        return violation;
    }

    public int getVL(CheckType hackType){
        if(violationLevels.containsKey(hackType)){
            return violationLevels.get(hackType);
        }
        violationLevels.put(hackType,0);
        return 0;
    }

}
