package me.norrapat.employer.controller;

import me.norrapat.employer.entity.User;
import me.norrapat.employer.service.EmployeeService;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testCreateEmployee_thenSuccess() throws Exception {
        User user = new User();
        user.setUsername("charge");
        user.setName("Norrapat");
        user.setPassword("Password");

        when(employeeService.createEmployee(any(User.class))).thenReturn(user);

        ResultActions result = mockMvc.perform(post("/api/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(user)));

        result.andExpect(status().isCreated());
        verify(employeeService).createEmployee(any(User.class));
    }
}
