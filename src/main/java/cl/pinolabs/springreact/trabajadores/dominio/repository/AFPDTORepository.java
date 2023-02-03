package cl.pinolabs.springreact.trabajadores.dominio.repository;

import cl.pinolabs.springreact.trabajadores.dominio.dto.AFPDTO;

import java.util.List;
import java.util.Optional;

public interface AFPDTORepository {
    Optional<List<AFPDTO>> findAll();
    Optional<AFPDTO> findById(int id);
    AFPDTO save(AFPDTO afpdto);
    void delete(int id);
    boolean exitsByNombre(String nombre);
}
