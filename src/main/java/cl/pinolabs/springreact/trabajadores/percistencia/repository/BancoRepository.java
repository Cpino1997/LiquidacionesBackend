package cl.pinolabs.springreact.trabajadores.percistencia.repository;

import cl.pinolabs.springreact.trabajadores.dominio.dto.BancoDTO;
import cl.pinolabs.springreact.trabajadores.dominio.repository.BancoDTORepository;
import cl.pinolabs.springreact.trabajadores.percistencia.crud.BancoCrud;
import cl.pinolabs.springreact.trabajadores.percistencia.mapper.BancoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BancoRepository implements BancoDTORepository {
    private final BancoMapper mapper;
    private final BancoCrud crud;

    public BancoRepository(BancoMapper mapper, BancoCrud crud) {
        this.mapper = mapper;
        this.crud = crud;
    }

    @Override
    public Optional<List<BancoDTO>> findAll() {
        return Optional.of(mapper.toBancos(crud.findAll()));
    }

    @Override
    public Optional<BancoDTO> findById(int id) {
        return crud.findById(id)
                .map(mapper::toBanco);
    }

    @Override
    public BancoDTO save(BancoDTO bancoDTO) {
        return mapper.toBanco(crud.save(mapper.toBancoDTO(bancoDTO)));
    }

    @Override
    public void delete(int id) {
        crud.deleteById(id);
    }
}
