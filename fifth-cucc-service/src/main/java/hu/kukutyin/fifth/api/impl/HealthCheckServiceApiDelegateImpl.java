package hu.kukutyin.fifth.api.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hu.kukutyin.fifth.api.HealthCheckServiceApi;
import hu.kukutyin.fifth.api.HealthCheckServiceApiDelegate;

/**
 * install for Lombok plugin on IDEA
 * see https://howtodoinjava.com/spring-boot2/logging/logging-with-lombok/
 */
@Slf4j
@Service
public class HealthCheckServiceApiDelegateImpl implements HealthCheckServiceApiDelegate {

    /**
     * GET /healthcheck
     * Health Check
     *
     * @return Success (status code 200)
     * @see HealthCheckServiceApi#healthCheck()
     */
    @Override
    public ResponseEntity<Void> healthCheck() {
        log.info("healthCheck: {}", this.getClass());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
