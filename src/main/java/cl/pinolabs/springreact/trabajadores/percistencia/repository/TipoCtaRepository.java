package cl.pinolabs.springreact.trabajadores.percistencia.repository;

import cl.pinolabs.springreact.trabajadores.dominio.dto.TipoCuentaDTO;
import cl.pinolabs.springreact.trabajadores.dominio.repository.TipoCtaDTORepository;
import cl.pinolabs.springreact.trabajadores.percistencia.crud.TipoCuentaCrud;
import cl.pinolabs.springreact.trabajadores.percistencia.mapper.TipoCuentaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TipoCtaRepository implements TipoCtaDTORepository {
    private final TipoCuentaMapper mapper;
    private final TipoCuentaCrud crud;

    public TipoCtaRepository(TipoCuentaMapper mapper, TipoCuentaCrud crud) {
        this.mapper = mapper;
        this.crud = crud;
    }

    @Override
    public Optional<List<TipoCuentaDTO>> findAll() {
        return Optional.of(mapper.toTipoCuentas(crud.findAll()));
    }

    @Override
    public Optional<TipoCuentaDTO> findById(int id) {
        return crud.findById(id)
                .map(mapper::toTipoCuenta);
    }

    @Override
    public TipoCuentaDTO save(TipoCuentaDTO saludDTO) {
        return mapper.toTipoCuenta(crud.save(mapper.toTipoDTO(saludDTO)));
    }

    @Override
    public void delete(int id) {
        crud.deleteById(id);
    }
}
