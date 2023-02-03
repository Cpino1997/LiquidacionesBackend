package cl.pinolabs.springreact.trabajadores.percistencia.crud;

import cl.pinolabs.springreact.trabajadores.percistencia.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoCrud extends JpaRepository<Cargo, Integer> {
}
