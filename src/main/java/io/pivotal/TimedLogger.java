package io.pivotal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
final class TimedLogger {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AtomicLong counter = new AtomicLong();

    @Scheduled(fixedRate = 1000)
    void print() {
        this.logger.debug("Current count: {}", this.counter.getAndIncrement());
    }

}
