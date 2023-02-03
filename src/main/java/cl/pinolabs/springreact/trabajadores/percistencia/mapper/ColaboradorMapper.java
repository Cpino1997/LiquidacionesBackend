package cl.pinolabs.springreact.trabajadores.percistencia.mapper;

import cl.pinolabs.springreact.trabajadores.dominio.dto.ColaboradoresDTO;
import cl.pinolabs.springreact.trabajadores.percistencia.entity.Colaboradores;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AFPMapper.class, BancoMapper.class, CargoMapper.class, SaludMapper.class, TipoCuentaMapper.class})
public interface ColaboradorMapper {
    ColaboradoresDTO toColaborador(Colaboradores colaborador);
    List<ColaboradoresDTO> toColaboradores(List<Colaboradores> colaboradores);
    @InheritInverseConfiguration
    Colaboradores toColaboradorDTO(ColaboradoresDTO colaborador);
}
