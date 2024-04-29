package com.organizacionname.AppSB.Models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class UsuariosModels {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String nombre;
    private String email;
    private String password;
    private Integer estatus;
    private Date fecharegistro;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="Usuarioperfil", 
                joinColumns = @JoinColumn(name="idusuario"),  
                inverseJoinColumns = @JoinColumn(name="idperfil"))  
    private List<PerfilesModels> perfiles;

    public void agregar(PerfilesModels tempPerfiles){
        if (perfiles == null) {
            perfiles = new LinkedList<PerfilesModels>();
        }
        perfiles.add(tempPerfiles); 
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getEstatus() {
        return estatus;
    }
    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }
    public Date getFechaRegistro() {
        return fecharegistro;
    }
    public void setFechaRegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }
    public List<PerfilesModels> getPerfiles() {
        return perfiles;
    }
    public void setPerfiles(List<PerfilesModels> perfiles) {
        this.perfiles = perfiles;
    }
    @Override
    public String toString() {
        return "UsuariosModels [id=" + id + ", username=" + username + ", nombre=" + nombre + ", email=" + email
                + ", password=" + password + ", estatus=" + estatus + ", fecharegistro=" + fecharegistro + ", perfiles="
                + perfiles + "]";
    }
    
 
     

}
