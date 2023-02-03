package cl.pinolabs.springreact.security.modelos.percistencia.crud;

import cl.pinolabs.springreact.security.modelos.percistencia.entity.ERole;
import cl.pinolabs.springreact.security.modelos.percistencia.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleCrud extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}