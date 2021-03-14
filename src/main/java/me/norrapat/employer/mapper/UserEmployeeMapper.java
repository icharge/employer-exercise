package me.norrapat.employer.mapper;

import me.norrapat.employer.dto.EmployeeDto;
import me.norrapat.employer.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserEmployeeMapper {

    UserEmployeeMapper MAPPER = Mappers.getMapper(UserEmployeeMapper.class);

    @Mapping(target = "password", ignore = true)
    EmployeeDto toEmployee(User source);

    @Mapping(target = "password", ignore = true)
    List<EmployeeDto> toEmployee(List<User> source);

    User toUser(EmployeeDto source);
}
