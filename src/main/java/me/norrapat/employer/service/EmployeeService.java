package me.norrapat.employer.service;

import me.norrapat.employer.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    List<User> findAllEmployee();

    User findEmployeeById(Long id);

    User saveEmployee(User user);
}
