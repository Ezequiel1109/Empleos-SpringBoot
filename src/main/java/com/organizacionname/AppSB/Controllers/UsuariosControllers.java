package com.organizacionname.AppSB.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.organizacionname.AppSB.Models.*;
import com.organizacionname.AppSB.Services.IUsuariosServices;

@Controller
@RequestMapping("/usuarios")
public class UsuariosControllers {
    
    @Autowired
	private IUsuariosServices serviceUsuarios;
	
    
   @GetMapping("/index")
	public String mostrarIndex(Model model) {
    	List<UsuariosModels> lista = serviceUsuarios.buscarTodos();
    	model.addAttribute("usuarios", lista);
		return "usuarios/listUsuarios";
	}
    
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		    	
		// Eliminamos el usuario
    	serviceUsuarios.eliminar(idUsuario);			
		attributes.addFlashAttribute("msg", "El usuario fue eliminado!.");
		return "redirect:/usuarios/index";
	}

}
