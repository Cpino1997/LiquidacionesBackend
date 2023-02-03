package cl.pinolabs.springreact.trabajadores.percistencia.mapper;

import cl.pinolabs.springreact.trabajadores.dominio.dto.SaludDTO;
import cl.pinolabs.springreact.trabajadores.percistencia.entity.Salud;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface SaludMapper {
    SaludDTO toSalud(Salud salud);
    List<SaludDTO> toSaluds(List<Salud> saluds);
    @InheritInverseConfiguration
    Salud toSaludDTO(SaludDTO salud);
}
