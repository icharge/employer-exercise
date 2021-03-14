package me.norrapat.employer.service;

import lombok.extern.log4j.Log4j2;
import me.norrapat.employer.entity.User;
import me.norrapat.employer.exception.NotFoundEmployeeException;
import me.norrapat.employer.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> findAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User findUserById(int id) {
        return userRepo.findById(id).orElseThrow(() -> new NotFoundEmployeeException(id));
    }


}
