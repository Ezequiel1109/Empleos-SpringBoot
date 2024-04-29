package com.organizacionname.AppSB.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.organizacionname.AppSB.Models.UsuariosModels;


@Service
public interface IUsuariosServices {
    List<UsuariosModels> buscarTodos();
    void guardar(UsuariosModels usuariosModels);
    void eliminar(Integer idUsuario);
    UsuariosModels buscarPorUsername(String username);
}
