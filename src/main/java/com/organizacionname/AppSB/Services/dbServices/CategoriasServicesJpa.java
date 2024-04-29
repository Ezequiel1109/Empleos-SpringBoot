package com.organizacionname.AppSB.Services.dbServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.organizacionname.AppSB.Models.CategoriasModels;
import com.organizacionname.AppSB.Repository.CategoriasRepository;
import com.organizacionname.AppSB.Services.ICategoriasService;

@Service
@Primary
public class CategoriasServicesJpa implements ICategoriasService{
    
    @Autowired
    private CategoriasRepository catRepo;

    @Override
    public List<CategoriasModels> buscarTodas() {
        return catRepo.findAll();
    }

    @Override
    public CategoriasModels buscarPorId(Integer idCategoria) {
        Optional<CategoriasModels> optional =  catRepo.findById(idCategoria);
        
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new RuntimeException("No se encontro la categoria con el ID: " + idCategoria);
        }
        
    }

    @Override
    public void guardar(CategoriasModels categoriasModels) {
        catRepo.save(categoriasModels);
    }

    @Override
    public void eliminar(Integer idCategoria) {
       catRepo.deleteById(idCategoria);
    }

    @Override
    public Page<CategoriasModels> buscarTodas(Pageable page) {
        return catRepo.findAll(page);
    }

}
