package cl.pinolabs.springreact.trabajadores.dominio.dto;

import lombok.Data;

@Data
public class CargoDTO {
    private Integer id;
    private String cargo;
    private Integer sueldo;
}
