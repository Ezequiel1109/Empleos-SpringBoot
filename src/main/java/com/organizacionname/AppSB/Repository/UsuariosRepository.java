package com.organizacionname.AppSB.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizacionname.AppSB.Models.UsuariosModels;

public interface UsuariosRepository extends JpaRepository<UsuariosModels, Integer>{
    UsuariosModels findByUsername(String username);
}
