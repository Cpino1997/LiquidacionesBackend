package cl.pinolabs.springreact.trabajadores.dominio.repository;


import cl.pinolabs.springreact.trabajadores.dominio.dto.SaludDTO;

import java.util.List;
import java.util.Optional;

public interface SaludDTORepository {
    Optional<List<SaludDTO>> findAll();
    Optional<SaludDTO> findById(int id);
    SaludDTO save(SaludDTO saludDTO);
    void delete(int id);
}
