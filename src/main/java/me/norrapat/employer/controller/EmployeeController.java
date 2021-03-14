package me.norrapat.employer.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.norrapat.employer.dto.EmployeeDto;
import me.norrapat.employer.dto.EmployeeResponseDto;
import me.norrapat.employer.entity.User;
import me.norrapat.employer.mapper.UserEmployeeMapper;
import me.norrapat.employer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("Employee")
@Tag(name = "Employee API")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Tag(name = "Employee API")
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll() {
        List<User> allUser = employeeService.findAllUser();

        List<EmployeeDto> employeeDtoList = UserEmployeeMapper.MAPPER.toEmployee(allUser);

        return ResponseEntity.ok(
                EmployeeResponseDto.builder()
                        .employeeList(employeeDtoList)
                        .build()
        );
    }
}
