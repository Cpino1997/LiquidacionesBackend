package cl.pinolabs.springreact.trabajadores.percistencia.mapper;

import cl.pinolabs.springreact.trabajadores.dominio.dto.TipoCuentaDTO;
import cl.pinolabs.springreact.trabajadores.percistencia.entity.TipoCuentas;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoCuentaMapper {
    TipoCuentaDTO toTipoCuenta(TipoCuentas tipo);
    List<TipoCuentaDTO> toTipoCuentas(List<TipoCuentas> tipos);
    @InheritInverseConfiguration
    TipoCuentas toTipoDTO(TipoCuentaDTO tipo);
}
