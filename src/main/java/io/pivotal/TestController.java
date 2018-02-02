package io.pivotal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Random;

@RestController
final class TestController {

    private static final Random RANDOM = new SecureRandom();

    @GetMapping("/test")
    String test() throws InterruptedException {
        int delay = RANDOM.nextInt(1_000);
        Thread.sleep(delay);
        return String.valueOf(delay);
    }

}
