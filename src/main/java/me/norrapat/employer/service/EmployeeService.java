package me.norrapat.employer.service;

import me.norrapat.employer.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    List<User> findAllUser();

    User findUserById(int id);
}
