package me.norrapat.employer.controller;

import me.norrapat.employer.dto.GenericResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloHttpTest {

    @LocalServerPort
    private int port;

    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void helloShouldReturnHelloMessage() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + contextPath, GenericResponse.class))
                .extracting("message")
                .hasNoNullFieldsOrProperties()
                .isEqualTo("Hello");
    }

}
