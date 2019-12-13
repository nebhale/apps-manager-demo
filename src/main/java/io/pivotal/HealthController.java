/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.pivotal;

import org.springframework.boot.actuate.health.Status;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
final class HealthController {

    private final MutableCompositeHealthContributor mutableCompositeHealthContributor;

    private final MutableHealthIndicator mutableHealthIndicator;

    HealthController(MutableHealthIndicator mutableHealthIndicator, MutableCompositeHealthContributor mutableCompositeHealthContributor) {
        this.mutableHealthIndicator = mutableHealthIndicator;
        this.mutableCompositeHealthContributor = mutableCompositeHealthContributor;
    }

    @PostMapping("/health/mutate")
    Mono<Void> mutate(@RequestParam Status status, @RequestBody Map<String, Object> details) {
        return this.mutableHealthIndicator.mutate(status, details);
    }

    @PostMapping("/health/composite/{name}/mutate")
    Mono<Void> mutate(@PathVariable String name, @RequestParam Status status, @RequestBody Map<String, Object> details) {
        return this.mutableCompositeHealthContributor.mutate(name, status, details);
    }

}
