package hu.kukutyin.fifth.api.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hu.kukutyin.fifth.api.GreetingServiceApi;
import hu.kukutyin.fifth.api.GreetingServiceApiDelegate;

@Slf4j
@Service
public class GreetingServiceApiDelegateImpl implements GreetingServiceApiDelegate {

    /**
     * GET /greeting/
     * Greeting controller
     *
     * @return Success (status code 200)
     * or Internal server error (status code 500)
     * @see GreetingServiceApi#greeting
     */
    @Override
    public ResponseEntity<String> greeting() {
        log.info("Hello World! :)");
        return new ResponseEntity<>("Hello World! :)", HttpStatus.OK);
    }

    /**
     * GET /hello/{helloName}
     * Greeting controller
     *
     * @param helloName Hello: ${helloName} (required)
     * @return Success (status code 200)
     * or Internal server error (status code 500)
     * @see GreetingServiceApi#hello
     */
    @Override
    public ResponseEntity<String> hello(String helloName) {
        log.info("Hello: {}", helloName);
        return new ResponseEntity<>(String.format("Hello: %s", helloName), HttpStatus.OK);
    }
}
