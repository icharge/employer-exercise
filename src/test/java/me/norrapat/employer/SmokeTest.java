package me.norrapat.employer;

import me.norrapat.employer.controller.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private HelloController helloController;

    @Test
    public void contextLoads() {
        assertThat(helloController).isNotNull();
    }

}
