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

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
final class MutableHealthIndicator extends AbstractHealthIndicator {

    private volatile Map<String, Object> details = Collections.emptyMap();

    private volatile Status status = Status.UP;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.status(this.status);
        this.details.forEach(builder::withDetail);
    }

    void mutate(Status status, Map<String, Object> details) {
        this.status = status;
        this.details = details;
    }

}
