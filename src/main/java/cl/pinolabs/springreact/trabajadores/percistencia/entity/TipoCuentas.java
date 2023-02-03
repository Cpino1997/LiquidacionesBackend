package cl.pinolabs.springreact.trabajadores.percistencia.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_cuentas")
public class TipoCuentas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
