package cl.pinolabs.springreact.trabajadores.percistencia.repository;

import cl.pinolabs.springreact.trabajadores.dominio.dto.ColaboradoresDTO;
import cl.pinolabs.springreact.trabajadores.dominio.repository.ColaboradoresDTORepository;
import cl.pinolabs.springreact.trabajadores.percistencia.crud.ColaboradorCrud;
import cl.pinolabs.springreact.trabajadores.percistencia.mapper.ColaboradorMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ColaboradoresRepository implements ColaboradoresDTORepository {
    private final ColaboradorMapper mapper;
    private final ColaboradorCrud crud;

    public ColaboradoresRepository(ColaboradorMapper mapper, ColaboradorCrud crud) {
        this.mapper = mapper;
        this.crud = crud;
    }
    @Override
    public Optional<List<ColaboradoresDTO>> findAll() {
        return Optional.of(mapper.toColaboradores(crud.findAll()));
    }

    @Override
    public Optional<ColaboradoresDTO> findById(int id) {
        return crud.findById(id)
                .map(mapper::toColaborador);
    }

    @Override
    public ColaboradoresDTO save(ColaboradoresDTO colaboradoresDTO) {
        return mapper.toColaborador(crud.save(mapper.toColaboradorDTO(colaboradoresDTO)));
    }

    @Override
    public void delete(int id) {
        crud.deleteById(id);
    }
}
