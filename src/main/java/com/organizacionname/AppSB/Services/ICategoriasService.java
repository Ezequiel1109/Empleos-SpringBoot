package com.organizacionname.AppSB.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.organizacionname.AppSB.Models.CategoriasModels;

@Service
public interface ICategoriasService {
    List<CategoriasModels> buscarTodas();
    CategoriasModels  buscarPorId(Integer idCategoria);
    void guardar(CategoriasModels categoriasModels);
    void eliminar(Integer idCategoria);
    Page<CategoriasModels> buscarTodas(Pageable page);
}
