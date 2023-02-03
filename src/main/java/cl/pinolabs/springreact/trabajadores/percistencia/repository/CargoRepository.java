package cl.pinolabs.springreact.trabajadores.percistencia.repository;

import cl.pinolabs.springreact.trabajadores.dominio.dto.CargoDTO;
import cl.pinolabs.springreact.trabajadores.dominio.repository.CargoDTORepository;
import cl.pinolabs.springreact.trabajadores.percistencia.crud.CargoCrud;
import cl.pinolabs.springreact.trabajadores.percistencia.mapper.CargoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class CargoRepository implements CargoDTORepository {

    private final CargoMapper mapper;
    private final CargoCrud crud;

    public CargoRepository(CargoMapper mapper, CargoCrud crud) {
        this.mapper = mapper;
        this.crud = crud;
    }

    @Override
    public Optional<List<CargoDTO>> findAll() {
        return Optional.of(mapper.toCargos(crud.findAll()));
    }

    @Override
    public Optional<CargoDTO> findById(int id) {
        return crud.findById(id)
                .map(mapper::toCargo);
    }

    @Override
    public CargoDTO save(CargoDTO cargoDTO) {
        return mapper.toCargo(crud.save(mapper.toCargoDTO(cargoDTO)));
    }

    @Override
    public void delete(int id) {
        crud.deleteById(id);
    }
}
