package cl.pinolabs.springreact.security.modelos.percistencia.mapper;

import cl.pinolabs.springreact.security.modelos.dominio.dto.UserMinimalDTO;
import cl.pinolabs.springreact.security.modelos.percistencia.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMinimalDTO toUser(User usuario);
    List<UserMinimalDTO> toUsers(List<User> usuarios);
    @InheritInverseConfiguration
    User toUserDTO(UserMinimalDTO usuario);
}
