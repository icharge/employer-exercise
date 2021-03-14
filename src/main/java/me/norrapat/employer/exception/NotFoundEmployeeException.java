package me.norrapat.employer.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@AllArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundEmployeeException extends RuntimeException {

    private Integer id;

    @Override
    public String getMessage() {
        return String.format("Not found employee id [%d]", id);
    }
}
