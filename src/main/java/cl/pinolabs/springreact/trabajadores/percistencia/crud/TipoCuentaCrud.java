package cl.pinolabs.springreact.trabajadores.percistencia.crud;

import cl.pinolabs.springreact.trabajadores.percistencia.entity.TipoCuentas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCuentaCrud extends JpaRepository<TipoCuentas, Integer> {
}
