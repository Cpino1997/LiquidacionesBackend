package cl.pinolabs.springreact.trabajadores.dominio.repository;


import cl.pinolabs.springreact.trabajadores.dominio.dto.TipoCuentaDTO;

import java.util.List;
import java.util.Optional;

public interface TipoCtaDTORepository {
    Optional<List<TipoCuentaDTO>> findAll();
    Optional<TipoCuentaDTO> findById(int id);
    TipoCuentaDTO save(TipoCuentaDTO tipoCtaDTO);
    void delete(int id);
}
