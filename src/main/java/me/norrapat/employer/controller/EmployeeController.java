package me.norrapat.employer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import me.norrapat.employer.dto.EmployeeDto;
import me.norrapat.employer.dto.EmployeeResponseDto;
import me.norrapat.employer.dto.GenericResponse;
import me.norrapat.employer.entity.User;
import me.norrapat.employer.mapper.UserEmployeeMapper;
import me.norrapat.employer.service.EmployeeService;
import me.norrapat.employer.util.EmployeeValidation;
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

    @PostMapping
    @ResponseBody
    @ApiOperation(
            value = "Create new employee",
            authorizations = @Authorization(value = "OAuth")
    )
    public ResponseEntity<GenericResponse> createNew(@RequestBody EmployeeDto employeeDto) {

        EmployeeValidation.validateCreate(employeeDto);

        employeeService.createEmployee(UserEmployeeMapper.MAPPER.toUser(employeeDto));

        return ResponseEntity.ok(GenericResponse.builder()
                .status("OK")
                .message("Saved")
                .build()
        );
    }


    @PostMapping("/{id}")
    @ResponseBody
    @ApiOperation(
            value = "Saving one employee",
            authorizations = @Authorization(value = "OAuth")
    )
    public ResponseEntity<GenericResponse> saveById(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {

        EmployeeValidation.validateSave(id, employeeDto);

        employeeService.saveEmployee(UserEmployeeMapper.MAPPER.toUser(employeeDto));

        return ResponseEntity.ok(GenericResponse.builder()
                .status("OK")
                .message("Saved")
                .build()
        );
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Delete one employee by ID",
            authorizations = @Authorization(value = "OAuth")
    )
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }

}
