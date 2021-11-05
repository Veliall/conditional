package com.example.conditional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Igor Khristiuk on 05.11.2021
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> devResponse =
                restTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(8080) +
                        "/profile", String.class);
        ResponseEntity<String> prodResponse =
                restTemplate.getForEntity("http://localhost:" + prodapp.getMappedPort(8081) +
                        "/profile", String.class);

        assertEquals("Current profile is dev", devResponse.getBody());
        assertEquals("Current profile is production", prodResponse.getBody());
    }

}
