/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.profile;

import lombok.Data;

@Data
public class IProfileData {

    private double[] clicksPerSecond = new double[]{0,0,0,0};
    private long bowPull = 0;
    private long bowShoot = 0;
    private double bowPower = 0;
    private double healthPerSecond = 0;
    private long lastGroundTime = 0;
    private double blocksPerSecond = 0;

}
