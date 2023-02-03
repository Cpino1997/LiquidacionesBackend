package cl.pinolabs.springreact.security.modelos.percistencia.repository;

import cl.pinolabs.springreact.security.modelos.dominio.dto.UserMinimalDTO;
import cl.pinolabs.springreact.security.modelos.dominio.repository.UserDTORepository;
import cl.pinolabs.springreact.security.modelos.percistencia.crud.UserCrud;
import cl.pinolabs.springreact.security.modelos.percistencia.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements UserDTORepository {
    private final UserMapper mapper;
    private final UserCrud crud;

    public UserRepository(UserMapper mapper, UserCrud crud) {
        this.mapper = mapper;
        this.crud = crud;
    }


    @Override
    public Optional<List<UserMinimalDTO>> findAll() {
        return Optional.of(mapper.toUsers(crud.findAll()));
    }

    @Override
    public Optional<UserMinimalDTO> findByUsername(String username) {
        return crud.findByUsername(username)
                .map(mapper::toUser);
    }

    @Override
    public boolean exitsByNombre(String nombre) {
        return crud.existsByUsername(nombre);
    }
}