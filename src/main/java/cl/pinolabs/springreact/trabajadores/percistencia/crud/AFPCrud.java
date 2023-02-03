package cl.pinolabs.springreact.trabajadores.percistencia.crud;

import cl.pinolabs.springreact.trabajadores.percistencia.entity.AFP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AFPCrud extends JpaRepository<AFP, Integer> {
    Boolean existsAfpByNombre(String username);
}
