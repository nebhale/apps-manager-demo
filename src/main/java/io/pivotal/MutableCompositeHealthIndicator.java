package io.pivotal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeHealthIndicator;
import org.springframework.boot.actuate.health.HealthAggregator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
final class MutableCompositeHealthIndicator extends CompositeHealthIndicator {

    private final Map<String, MutableHealthIndicator> indicators = new HashMap<>(2);

    @Autowired
    public MutableCompositeHealthIndicator(HealthAggregator healthAggregator) {
        super(healthAggregator);

        MutableHealthIndicator alpha = new MutableHealthIndicator();
        this.indicators.put("alpha", alpha);
        addHealthIndicator("alpha", alpha);

        MutableHealthIndicator bravo = new MutableHealthIndicator();
        this.indicators.put("bravo", bravo);
        addHealthIndicator("bravo", bravo);
    }

    public MutableCompositeHealthIndicator(HealthAggregator healthAggregator, Map<String, HealthIndicator> indicators) {
        super(healthAggregator, indicators);
    }

    void mutate(String name, Status status, Map<String, Object> details) {
        this.indicators.get(name).mutate(status, details);
    }

}
