package me.norrapat.employer.mapper;

import me.norrapat.employer.dto.EmployeeDto;
import me.norrapat.employer.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserEmployeeMapper {

    UserEmployeeMapper MAPPER = Mappers.getMapper(UserEmployeeMapper.class);

    EmployeeDto toEmployee(User source);

    List<EmployeeDto> toEmployee(List<User> source);
}
