package me.norrapat.employer.exception;

import lombok.Builder;
import lombok.Getter;
import me.norrapat.employer.dto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Builder
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEmployeeException extends RuntimeException {

    private final String fieldName;
    private final EmployeeDto dto;

    @Override
    public String getMessage() {
        return String.format("Invalid field %s : %s", fieldName, dto);
    }
}
