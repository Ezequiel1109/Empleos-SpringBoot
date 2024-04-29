package com.organizacionname.AppSB.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Perfiles")
public class PerfilesModels {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String perfil;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPerfil() {
        return perfil;
    }
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    @Override
    public String toString() {
        return "PerfilesModels [id=" + id + ", perfil=" + perfil + "]";
    }
    
}
