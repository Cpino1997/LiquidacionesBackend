package cl.pinolabs.springreact.trabajadores.percistencia.crud;

import cl.pinolabs.springreact.trabajadores.percistencia.entity.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoCrud extends JpaRepository<Banco, Integer> {
}
