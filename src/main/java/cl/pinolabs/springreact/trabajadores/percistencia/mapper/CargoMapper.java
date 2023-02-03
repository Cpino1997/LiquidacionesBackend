package cl.pinolabs.springreact.trabajadores.percistencia.mapper;

import cl.pinolabs.springreact.trabajadores.dominio.dto.CargoDTO;
import cl.pinolabs.springreact.trabajadores.percistencia.entity.Cargo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CargoMapper {
    CargoDTO toCargo(Cargo cargo);
    List<CargoDTO> cargos(List<Cargo> cargos);
    @InheritInverseConfiguration
    Cargo toCargoDTO(CargoDTO cargoDTO);
}
