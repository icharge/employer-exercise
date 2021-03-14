package me.norrapat.employer.service;

import lombok.extern.log4j.Log4j2;
import me.norrapat.employer.entity.User;
import me.norrapat.employer.exception.NotFoundEmployeeException;
import me.norrapat.employer.repository.UserRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAllEmployee() {
        return userRepo.findAll();
    }

    @Override
    public User findEmployeeById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new NotFoundEmployeeException(id));
    }

    @Override
    @Transactional
    public User createEmployee(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepo.save(user);
    }

    @Override
    @Transactional
    public User saveEmployee(User user) {

        User employee = findEmployeeById(user.getId());

        // Update password if not empty.
        if (StringUtils.isNotEmpty(user.getPassword())) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        } else {
            // Remain existing password.
            user.setPassword(employee.getPassword());
        }

        // TODO: Admin must allow to change role.
        user.setRole(employee.getRole());

        return userRepo.save(user);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        User employee = findEmployeeById(id);

        userRepo.delete(employee);
    }

}
