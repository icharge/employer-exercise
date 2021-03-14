package me.norrapat.employer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import me.norrapat.employer.dto.EmployeeDto;
import me.norrapat.employer.dto.EmployeeResponseDto;
import me.norrapat.employer.entity.User;
import me.norrapat.employer.mapper.UserEmployeeMapper;
import me.norrapat.employer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Employee API")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @ResponseBody
    @ApiOperation(
            value = "Retrieve all employee",
            authorizations = @Authorization(value = "OAuth")
    )
    public ResponseEntity<EmployeeResponseDto> findAll() {
        List<User> allEmployee = employeeService.findAllEmployee();

        List<EmployeeDto> employeeDtoList = UserEmployeeMapper.MAPPER.toEmployee(allEmployee);

        return ResponseEntity.ok(
                EmployeeResponseDto.builder()
                        .employeeList(employeeDtoList)
                        .build()
        );
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(
            value = "Retrieve one employee by ID",
            authorizations = @Authorization(value = "OAuth")
    )
    public ResponseEntity<?> findById(@PathVariable Long id) {
        User employee = employeeService.findEmployeeById(id);

        EmployeeDto employeeDto = UserEmployeeMapper.MAPPER.toEmployee(employee);

        return ResponseEntity.ok(employeeDto);
    }
}
