package io.pivotal;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Random;

@RestController
final class TestController {

    private static final Random RANDOM = new SecureRandom();

    private final Timer timer;

    TestController(MetricRegistry metricRegistry) {
        this.timer = metricRegistry.timer("histogram.response.test");
    }

    @GetMapping("/test")
    String test() throws InterruptedException {
        try (Timer.Context context = this.timer.time()) {
            int delay = RANDOM.nextInt(1_000);
            Thread.sleep(delay);
            return String.valueOf(delay);
        }
    }

}
