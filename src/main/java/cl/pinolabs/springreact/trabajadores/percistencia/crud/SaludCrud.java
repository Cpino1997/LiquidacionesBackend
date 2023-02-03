package cl.pinolabs.springreact.trabajadores.percistencia.crud;

import cl.pinolabs.springreact.trabajadores.percistencia.entity.Salud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaludCrud extends JpaRepository<Salud, Integer> {
}
