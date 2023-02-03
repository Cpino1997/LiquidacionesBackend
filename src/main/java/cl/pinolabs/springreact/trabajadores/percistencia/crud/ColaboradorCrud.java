package cl.pinolabs.springreact.trabajadores.percistencia.crud;

import cl.pinolabs.springreact.trabajadores.percistencia.entity.Colaboradores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorCrud extends JpaRepository<Colaboradores, Integer> {
}
