package com.organizacionname.AppSB.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class CategoriasModels {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;

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
    @Override
    public String toString() {
        return "CategoriaModels [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }
}