package cl.pinolabs.springreact.security.modelos.dominio.repository;

import cl.pinolabs.springreact.security.modelos.dominio.dto.UserMinimalDTO;

import java.util.List;
import java.util.Optional;

public interface UserDTORepository {
    Optional<List<UserMinimalDTO>> findAll();
    Optional<UserMinimalDTO> findByUsername(String username);

    boolean exitsByNombre(String nombre);
}
