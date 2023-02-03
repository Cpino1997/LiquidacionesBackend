package cl.pinolabs.springreact.trabajadores.dominio.dto;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ColaboradoresDTO {

    /* Propiedades de los colaboradores */
    private Integer id;
    private String nombre;
    private Integer Rut;
    private LocalDate fecha_nacimiento;
    private String direccion;
    private LocalDate fecha_ingreso;
    private String correo;
    private boolean estado;

    /* Identificador de objetos + objeto */
    private Integer idCargo;
    private CargoDTO cargo;
    private Integer idBanco;
    private BancoDTO banco;
    private Integer idTipoCuenta;
    private TipoCuentaDTO tipoCuenta;
    private Integer idSalud;
    private SaludDTO salud;
    private Integer idAFP;
    private AFPDTO afp;
}
