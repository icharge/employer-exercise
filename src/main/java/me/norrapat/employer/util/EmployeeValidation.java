package me.norrapat.employer.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.norrapat.employer.dto.EmployeeDto;
import me.norrapat.employer.entity.User;
import me.norrapat.employer.exception.InvalidEmployeeException;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeValidation {

    public static void validateCreate(EmployeeDto employeeDto) {
        // Don't set ID
        employeeDto.setId(null);

        if (StringUtils.isEmpty(employeeDto.getName())) {
            throw InvalidEmployeeException.builder()
                    .fieldName("name")
                    .dto(employeeDto)
                    .build();
        } else if (StringUtils.isEmpty(employeeDto.getUsername())) {
            throw InvalidEmployeeException.builder()
                    .fieldName("username")
                    .dto(employeeDto)
                    .build();
        } else if (StringUtils.isEmpty(employeeDto.getPassword())) {
            throw InvalidEmployeeException.builder()
                    .fieldName("password")
                    .dto(employeeDto)
                    .build();
        }

        try {
            User.Role.valueOf(employeeDto.getRole());
        } catch (IllegalArgumentException e) {
            employeeDto.setRole("USER");
        }
    }

    public static void validateSave(Long id, EmployeeDto employeeDto) {
        // Set ID.
        employeeDto.setId(id);

        try {
            User.Role.valueOf(employeeDto.getRole());
        } catch (IllegalArgumentException e) {
            employeeDto.setRole("USER");
        }
    }

}
