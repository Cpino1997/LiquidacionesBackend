package cl.pinolabs.springreact.trabajadores.percistencia.mapper;

import cl.pinolabs.springreact.trabajadores.percistencia.entity.Salud;
import org.mapstruct.InheritInverseConfiguration;

import java.util.List;

public interface SaludMapper {
    SaludMapper toSalud(Salud salud);
    List<SaludMapper> toSaluds(List<Salud> saluds);
    @InheritInverseConfiguration
    Salud toSaludDTO(SaludMapper salud);
}
