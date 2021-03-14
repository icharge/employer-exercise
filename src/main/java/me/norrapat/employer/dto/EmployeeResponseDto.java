package me.norrapat.employer.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EmployeeResponseDto {

    private final List<EmployeeDto> employeeList;

}
