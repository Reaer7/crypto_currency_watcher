package com.gmail.brutskiart.watcher.event;

import org.springframework.context.ApplicationEvent;

public class PulledCoinPriceEvent extends ApplicationEvent {

    public PulledCoinPriceEvent(Object source) {
        super(source);
    }
}
