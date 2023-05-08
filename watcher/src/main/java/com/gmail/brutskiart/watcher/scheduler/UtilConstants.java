package com.gmail.brutskiart.watcher.scheduler;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UtilConstants {

    public static final String NOTIFY_TEMPLATE = "Symbol of coin: %s\tUsername: %s\tChange percent: %.03f";
    public static final double PERCENT_FOR_NOTIFY = 1.00;
    public static final int PERCENT_MULTIPLY = 100;
    public static final int PERCENT_SCALE = 3;
}
