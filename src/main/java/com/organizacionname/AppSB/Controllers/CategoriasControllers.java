package com.organizacionname.AppSB.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.organizacionname.AppSB.Models.CategoriasModels;
import com.organizacionname.AppSB.Services.ICategoriasService;

@Controller
@RequestMapping("/categorias")
public class CategoriasControllers {

    @Autowired
    //@Qualifier("categoriasServicesJpa") se utiliza para resolver la ambigüedad cuando hay más de un bean de un mismo tipo en el contexto de la aplicación y se necesita especificar cuál de ellos debe ser inyectado en un punto específico.
    private ICategoriasService serviceCategorias;
    
    @GetMapping("/index")
    //@RequestMapping(value="/index", method=RequestMethod.GET)
    public String listadoDeCategoria(Model model) {
        List<CategoriasModels> listaC = serviceCategorias.buscarTodas();
        model.addAttribute("categorias", listaC);
        return "categorias/listCategorias";
    }
    @GetMapping("/create")
    //@RequestMapping(value="/create", method=RequestMethod.GET)
    public String crearCategoria(CategoriasModels categoriasModels) {
    return "categorias/formCategorias";
    }
    @PostMapping("/save")
    //@RequestMapping(value="/save", method=RequestMethod.POST)
    public String guardarCategoria(CategoriasModels categoriasModels, BindingResult result, RedirectAttributes  attributes){
         if (result.hasErrors()) {
            // ver el error por consola
            for(ObjectError error: result.getAllErrors()){
                System.out.println("Error al guardar la categoria: "+ error.getDefaultMessage());
            }
            return "categorias/formCategorias";
        }
        serviceCategorias.guardar(categoriasModels);
        attributes.addFlashAttribute("msg", "Categoria Guardada");
        // ver el error por consola
        System.out.println("Categoria:" + categoriasModels);
        return "redirect:/categorias/index";
    }
    @GetMapping("/delete/{id}")
    public String eliminarCategoria(@PathVariable("id") int idCategoria, RedirectAttributes attributes) {
        System.out.println("Borrando categoria con ID: " + idCategoria);
        serviceCategorias.eliminar(idCategoria);
        attributes.addFlashAttribute("msg", "Registro eliminado");
        return "redirect:/categorias/index";
    }
    @GetMapping("/edit/{id}")
    public String editarCategoria(@PathVariable("id") int idCategoria, Model model){    
        CategoriasModels categoria = serviceCategorias.buscarPorId(idCategoria);
        model.addAttribute("categoriasModels", categoria);
        return "categorias/formCategorias";
    }
    @GetMapping("/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page){
        Page<CategoriasModels> lista = serviceCategorias.buscarTodas(page);
        model.addAttribute("categorias", lista);
        return "categorias/listCategorias";
    }
}
