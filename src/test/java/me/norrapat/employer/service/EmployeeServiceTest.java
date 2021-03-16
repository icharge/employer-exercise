package me.norrapat.employer.service;

import me.norrapat.employer.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void saveEmployeeTest() {
        User userTest = createUserTest();
        User savedUser = employeeService.saveEmployee(userTest);
        assertEquals(savedUser.getName(), userTest.getName());
    }

    private User createUserTest() {
        return new User(
                1L,
                "admin",
                passwordEncoder.encode("password"),
                "Admin",
                User.Role.USER
        );
    }
}
