package me.norrapat.employer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class EmployeeDto {

    private Long id;
    private String username;
    private String name;
    private String role;

}
