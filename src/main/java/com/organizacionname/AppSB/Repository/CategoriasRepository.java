package com.organizacionname.AppSB.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizacionname.AppSB.Models.CategoriasModels;
//public interface CategoriasRepository extends CrudRepository<CategoriasModels, Integer>
public interface CategoriasRepository extends JpaRepository<CategoriasModels, Integer>{

    
}
