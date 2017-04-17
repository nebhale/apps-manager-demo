package io.pivotal;

import org.springframework.boot.actuate.health.Status;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
final class HealthController {

    private final MutableHealthIndicator mutableHealthIndicator;

    private final MutableCompositeHealthIndicator mutableCompositeHealthIndicator;

    HealthController(MutableHealthIndicator mutableHealthIndicator, MutableCompositeHealthIndicator mutableCompositeHealthIndicator) {
        this.mutableHealthIndicator = mutableHealthIndicator;
        this.mutableCompositeHealthIndicator = mutableCompositeHealthIndicator;
    }

    @PostMapping("/health/mutate")
    void mutate(@RequestParam Status status, @RequestBody Map<String, Object> details) {
        this.mutableHealthIndicator.mutate(status, details);
    }

    @PostMapping("/health/composite/{name}/mutate")
    void mutate(@PathVariable String name, @RequestParam Status status, @RequestBody Map<String, Object> details) {
        this.mutableCompositeHealthIndicator.mutate(name, status, details);
    }

}
