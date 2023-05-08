package com.gmail.brutskiart.watcher.scheduler;

import com.gmail.brutskiart.watcher.event.PulledCoinPriceEvent;
import com.gmail.brutskiart.watcher.repository.dao.UserNotifyDao;
import com.gmail.brutskiart.watcher.repository.model.UserNotify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.gmail.brutskiart.watcher.scheduler.UtilConstants.NOTIFY_TEMPLATE;
import static com.gmail.brutskiart.watcher.scheduler.UtilConstants.PERCENT_FOR_NOTIFY;
import static com.gmail.brutskiart.watcher.scheduler.UtilConstants.PERCENT_MULTIPLY;
import static com.gmail.brutskiart.watcher.scheduler.UtilConstants.PERCENT_SCALE;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserNotifyListener {

    private final UserNotifyDao userNotifyDao;

    @EventListener
    @Transactional(readOnly = true)
    public void checkUserNotify(PulledCoinPriceEvent event) {
        List<UserNotify> userNotifies = userNotifyDao.findAll();
        for (UserNotify userNotify : userNotifies) {
            double changePercent = getChangePercent(userNotify);
            if (changePercent > PERCENT_FOR_NOTIFY) {
                log.warn(String.format(
                        NOTIFY_TEMPLATE,
                        userNotify.getCrypto().getSymbol(),
                        userNotify.getUsername(),
                        changePercent
                ));
            }
        }
    }

    private double getChangePercent(UserNotify userNotify) {
        BigDecimal priceUsd = userNotify.getPriceUsd();
        BigDecimal currentPriceUsd = userNotify.getCrypto().getPriceUsd();
        return priceUsd.subtract(currentPriceUsd)
                .abs()
                .multiply(BigDecimal.valueOf(PERCENT_MULTIPLY))
                .divide(priceUsd, RoundingMode.CEILING)
                .setScale(PERCENT_SCALE, RoundingMode.CEILING)
                .doubleValue();
    }
}
