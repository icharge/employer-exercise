package me.norrapat.employer.service;

import me.norrapat.employer.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface EmployeeService {
    List<User> findAllEmployee();

    User findEmployeeById(Long id);

    User createEmployee(User user);

    User saveEmployee(User user);

    @Transactional
    void deleteEmployee(Long id);
}
