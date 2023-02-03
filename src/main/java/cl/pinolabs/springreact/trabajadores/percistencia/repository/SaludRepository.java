package cl.pinolabs.springreact.trabajadores.percistencia.repository;

import cl.pinolabs.springreact.trabajadores.dominio.dto.SaludDTO;
import cl.pinolabs.springreact.trabajadores.dominio.repository.SaludDTORepository;
import cl.pinolabs.springreact.trabajadores.percistencia.crud.SaludCrud;
import cl.pinolabs.springreact.trabajadores.percistencia.mapper.SaludMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SaludRepository implements SaludDTORepository {
    private final SaludMapper mapper;
    private final SaludCrud crud;

    public SaludRepository(SaludMapper mapper, SaludCrud crud) {
        this.mapper = mapper;
        this.crud = crud;
    }

    @Override
    public Optional<List<SaludDTO>> findAll() {
        return Optional.of(mapper.toSaluds(crud.findAll()));
    }

    @Override
    public Optional<SaludDTO> findById(int id) {
        return crud.findById(id)
                .map(mapper::toSalud);
    }

    @Override
    public SaludDTO save(SaludDTO saludDTO) {
        return mapper.toSalud(crud.save(mapper.toSaludDTO(saludDTO)));
    }

    @Override
    public void delete(int id) {
        crud.deleteById(id);
    }
}
