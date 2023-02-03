package cl.pinolabs.springreact.trabajadores.dominio.repository;


import cl.pinolabs.springreact.trabajadores.dominio.dto.ColaboradoresDTO;

import java.util.List;
import java.util.Optional;

public interface ColaboradoresDTORepository {
    Optional<List<ColaboradoresDTO>> findAll();
    Optional<ColaboradoresDTO> findById(int id);
    ColaboradoresDTO save(ColaboradoresDTO colaboradoresDTO);
    void delete(int id);
}
