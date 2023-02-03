package cl.pinolabs.springreact.trabajadores.percistencia.mapper;

import cl.pinolabs.springreact.trabajadores.dominio.dto.AFPDTO;
import cl.pinolabs.springreact.trabajadores.percistencia.entity.AFP;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AFPMapper {
    AFPDTO toAFP(AFP afp);
    List<AFPDTO> toAFPs(List<AFP> afps);
    @InheritInverseConfiguration
    AFP toAFPDT(AFPDTO afpdto);
}
