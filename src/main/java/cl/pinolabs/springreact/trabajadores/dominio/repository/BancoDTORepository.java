package cl.pinolabs.springreact.trabajadores.dominio.repository;


import cl.pinolabs.springreact.trabajadores.dominio.dto.BancoDTO;

import java.util.List;
import java.util.Optional;

public interface BancoDTORepository {
    Optional<List<BancoDTO>> findAll();
    Optional<BancoDTO> findById(int id);
    BancoDTO save(BancoDTO bancoDTO);
    void delete(int id);
}
