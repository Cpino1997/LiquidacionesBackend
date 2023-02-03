package cl.pinolabs.springreact.trabajadores.percistencia.mapper;

import cl.pinolabs.springreact.trabajadores.dominio.dto.BancoDTO;
import cl.pinolabs.springreact.trabajadores.percistencia.entity.Banco;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BancoMapper {
    BancoDTO toBanco(Banco banco);
    List<BancoDTO> toBancos(List<Banco> banco);
    @InheritInverseConfiguration
    Banco toBancoDTO(BancoDTO bancoDTO);
}
