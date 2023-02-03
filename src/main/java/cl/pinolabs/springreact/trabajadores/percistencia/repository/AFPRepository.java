package cl.pinolabs.springreact.trabajadores.percistencia.repository;

import cl.pinolabs.springreact.trabajadores.dominio.dto.AFPDTO;
import cl.pinolabs.springreact.trabajadores.dominio.repository.AFPDTORepository;
import cl.pinolabs.springreact.trabajadores.percistencia.crud.AFPCrud;
import cl.pinolabs.springreact.trabajadores.percistencia.mapper.AFPMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class AFPRepository implements AFPDTORepository {
    private final AFPMapper mapper;
    private final AFPCrud crud;

    public AFPRepository(AFPMapper mapper, AFPCrud crud) {
        this.mapper = mapper;
        this.crud = crud;
    }

    @Override
    public Optional<List<AFPDTO>> findAll() {
        return Optional.of(mapper.toAFPs(crud.findAll()));
    }

    @Override
    public Optional<AFPDTO> findById(int id) {
        return crud.findById(id)
                .map(mapper::toAFP);
    }

    @Override
    public AFPDTO save(AFPDTO afpdto) {
        return mapper.toAFP(crud.save(mapper.toAFPDT(afpdto)));
    }

    @Override
    public void delete(int id) {
        crud.deleteById(id);
    }

    @Override
    public boolean exitsByNombre(String nombre) {
        return crud.existsAfpByNombre(nombre);
    }
}
