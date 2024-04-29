package com.organizacionname.AppSB.Services.dbServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacionname.AppSB.Models.UsuariosModels;
import com.organizacionname.AppSB.Repository.UsuariosRepository;
import com.organizacionname.AppSB.Services.IUsuariosServices;

@Service
public class UsuariosServicesJpa implements IUsuariosServices{

    @Autowired
    private UsuariosRepository userRepo;

    public void guardar(UsuariosModels usuarioModels) {
		userRepo.save(usuarioModels);
	}

	public void eliminar(Integer idUsuario) {
		userRepo.deleteById(idUsuario);
	}

	public List<UsuariosModels> buscarTodos() {
		return userRepo.findAll();
	}

	@Override
	public UsuariosModels buscarPorUsername(String username) {
		return userRepo.findByUsername(username);
	}
    
}
