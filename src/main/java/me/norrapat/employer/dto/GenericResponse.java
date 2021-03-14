package me.norrapat.employer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponse {

    private String status;
    private String message;

}
