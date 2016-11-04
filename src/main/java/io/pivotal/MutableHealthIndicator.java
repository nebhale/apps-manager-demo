package io.pivotal;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
final class MutableHealthIndicator extends AbstractHealthIndicator {

    private volatile Status status = Status.UP;

    private volatile String detailKey;

    private volatile String detailValue;

    @RequestMapping(method = RequestMethod.POST, value = "/mutate")
    void mutate(@RequestParam Status status, @RequestParam(required = false) String detailKey, @RequestParam(required = false) String detailValue) {
        this.status = status;
        this.detailKey = detailKey;
        this.detailValue = detailValue;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.status(this.status);

        if (this.detailKey != null && this.detailValue != null) {
            builder.withDetail(this.detailKey, this.detailValue);
        }
    }

}
