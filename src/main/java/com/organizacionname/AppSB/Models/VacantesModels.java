package com.organizacionname.AppSB.Models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Vacantes")
public class VacantesModels {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private Double salario;
    private Integer destacado;
    private String imagen="no-image.png";
    private String estatus;
    private String detalles;
    //@Transient
    @OneToOne
    @JoinColumn(name="idcategoria")
    private CategoriasModels  categorias;
    
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
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Double getSalario() {
        return salario;
    }
    public void setSalario(Double salario) {
        this.salario = salario;
    }
    public Integer getDestacado() {
        return destacado;
    }
    public void setDestacado(Integer destacado) {
        this.destacado = destacado;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getEstatus() {
        return estatus;
    }
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    public String getDetalles() {
        return detalles;
    }
    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
    public CategoriasModels getCategorias() {
        return categorias;
    }
    public void setCategorias(CategoriasModels categorias) {
        this.categorias = categorias;
    }

    public void reset(){
        this.imagen=null;
    }


    //para ver en consola los datos entregados por la app
    @Override
    public String toString() {
        return "VacanteModels [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha
                + ", salario=" + salario + ", destacado=" + destacado + ", imagen=" + imagen +", estatus=" + estatus + 
                ", detalle=" + detalles +", categorias=" + categorias +"]";
    }
}
