package io.pivotal;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
final class MutableHealthIndicator extends AbstractHealthIndicator {

    private volatile Status status = Status.UP;

    private volatile Map<String, Object> details = Collections.emptyMap();

    void mutate(Status status, Map<String, Object> details) {
        this.status = status;
        this.details = details;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.status(this.status);
        this.details.forEach(builder::withDetail);
    }

}
