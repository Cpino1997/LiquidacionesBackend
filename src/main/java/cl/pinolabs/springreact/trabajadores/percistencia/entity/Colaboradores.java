package cl.pinolabs.springreact.trabajadores.percistencia.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "colaboradores")
public class Colaboradores {
    /* Propiedades de los colaboradores */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 40)
    private String nombre;

    @Column(nullable = false, length = 12)
    private Integer Rut;
    @Column(nullable = false)
    private LocalDate fecha_nacimiento;
    @Column(nullable = false,length = 80)
    private String direccion;
    @Column(nullable = false)
    private LocalDate fecha_ingreso;
    @Column(nullable = false ,unique = true)
    @Email
    private String correo;

    private boolean estado;

    /* Identificador de objetos + objeto */
    @Column(name = "id_cargo")
    private Integer idCargo;
    @ManyToOne
    @JoinColumn(name = "id_cargo", insertable = false, updatable = false)
    private Cargo cargo;
    @Column(name = "id_banco")
    private Integer idBanco;
    @ManyToOne
    @JoinColumn(name = "id_banco" , insertable = false, updatable = false)
    private Banco banco;
    @Column(name = "id_tipo_cuenta")
    private Integer idTipoCuenta;
    @ManyToOne
    @JoinColumn(name = "id_tipo_cuenta", insertable = false, updatable = false)
    private TipoCuentas tipoCuenta;

    @Column(name = "id_salud")
    private Integer idSalud;

    @ManyToOne
    @JoinColumn(name = "id_salud", insertable = false, updatable = false)
    private Salud salud;
    @Column(name = "id_afp")
    private Integer idAFP;

    @ManyToOne
    @JoinColumn(name = "id_afp", insertable = false, updatable = false)
    private AFP afp;

    @Column
    @UpdateTimestamp
    private Timestamp updatedOn;

    @Column
    @CreationTimestamp
    private Timestamp createdOn;
    /* Getters and Setters */

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

    public Integer getRut() {
        return Rut;
    }

    public void setRut(Integer rut) {
        Rut = rut;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Integer getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(Integer idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public TipoCuentas getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuentas tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Integer getIdSalud() {
        return idSalud;
    }

    public void setIdSalud(Integer idSalud) {
        this.idSalud = idSalud;
    }

    public Salud getSalud() {
        return salud;
    }

    public void setSalud(Salud salud) {
        this.salud = salud;
    }

    public Integer getIdAFP() {
        return idAFP;
    }

    public void setIdAFP(Integer idAFP) {
        this.idAFP = idAFP;
    }

    public AFP getAfp() {
        return afp;
    }

    public void setAfp(AFP afp) {
        this.afp = afp;
    }
}
