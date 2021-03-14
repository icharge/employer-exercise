package me.norrapat.employer.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.norrapat.employer.dto.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api("Hello")
@Tag(name = "Hello API")
@RestController
public class HelloController {

    @Tag(name = "Hello API")
    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<?> getHello() {
        return ResponseEntity.ok(GenericResponse.builder().message("Hello").build());
    }

}
