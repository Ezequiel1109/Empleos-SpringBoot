package com.organizacionname.AppSB.Models;


import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Solicitudes")
public class SolicitudesModels {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fecha; //fecha de la solicitud del usuario
    private String comentarios; 
    private String archivo; //el nombre del archivo PDF, DOCX del cv
    @OneToOne
    @JoinColumn(name="idvacante")
    private VacantesModels vacante; // foreingKEY en la tabla Solicitudes
    @OneToOne
    @JoinColumn(name="idusuario")
    private UsuariosModels usuario;//foreingKey en la tabla Solicitudes

    

    public SolicitudesModels() {
        this.fecha = LocalDate.now();
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getComentarios() {
        return comentarios;
    }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    public String getArchivo() {
        return archivo;
    }
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    public VacantesModels getVacante() {
        return vacante;
    }
    public void setVacante(VacantesModels vacante) {
        this.vacante = vacante;
    }
    public UsuariosModels getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuariosModels usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public String toString() {
        return "SolicitudesModels [id=" + id + ", fecha=" + fecha + ", comentarios=" + comentarios + ", archivo="
                + archivo + ", vacante=" + vacante + ", usuarios=" + usuario + "]";
    }
    
}
