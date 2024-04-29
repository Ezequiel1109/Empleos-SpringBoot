package com.organizacionname.AppSB.Controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.organizacionname.AppSB.Models.VacantesModels;
import com.organizacionname.AppSB.Services.*;
import com.organizacionname.AppSB.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesControllers {

    @Value("${AppSB.ruta.imagenes}")
    private String ruta;

    @Autowired
    private IVacantesService serviceVacantes;
    @Autowired
    private ICategoriasService serviceCategoria;

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model) {

        VacantesModels vacantesModels = serviceVacantes.buscarPorId(idVacante);

        System.out.println("Vacante: " + vacantesModels);
        model.addAttribute("vacante", vacantesModels);
        return "vacantes/detalle";
    }

    @GetMapping("/delete/{id}")
    public String eliminarConfirmacion(@PathVariable("id") int idVacante, RedirectAttributes attributes) {
        System.out.println("Borrando vacante con id:" + idVacante);
        serviceVacantes.eliminar(idVacante);
        attributes.addFlashAttribute("msg", "Registro Eliminado");
        return "redirect:/vacantes/indexPaginate";
    }
    @GetMapping("/edit/{id}")
    public String editarVacante(@PathVariable("id") int idVacante, Model model){
        VacantesModels  vacantes = serviceVacantes.buscarPorId(idVacante);
        model.addAttribute("vacantesModels", vacantes);
        return "vacantes/formVacante";
    }

    @GetMapping("/create")
    public String crearVacante(VacantesModels vacantesModels, Model model) {
        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardarVacante(VacantesModels vacantesModels, BindingResult result, RedirectAttributes attributes,
            @RequestParam("archivoImagen") MultipartFile multiPart) {
        if (result.hasErrors()) {
            // ver el error por consola
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error al guardar la vacante: " + error.getDefaultMessage());
            }
            return "vacantes/formVacante";
        }
        if (!multiPart.isEmpty()) {
            // String ruta = "/empleos/img-vacantes/"; // Linux/MAC
            // String ruta = "c:/empleos/img-vacantes/"; // Windows
            String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreImagen != null) { // La imagen si se subio
                // Procesamos la variable nombreImagen
                vacantesModels.setImagen(nombreImagen);
            }
        }
        serviceVacantes.guardar(vacantesModels);
        attributes.addFlashAttribute("msg", "Registro Guardado");
        System.out.println("Vacante:" + vacantesModels);
        return "redirect:/vacantes/indexPaginate";
    }
    @GetMapping("/indexPaginate")
    public String mostrarIndexPage(Model model, Pageable page){
        Page<VacantesModels> lista = serviceVacantes.buscarTodas(page);
        model.addAttribute("vacantes", lista);
        return "vacantes/listVacantes";
    }

    @GetMapping("/index")
    public String listadoDeVacantes(Model model) {
        List<VacantesModels> lista = serviceVacantes.buscarTodas();
        model.addAttribute("vacantes", lista);
        return "vacantes/listVacantes";
    }

    @ModelAttribute
    public void setGenerico(Model model){
        model.addAttribute("categorias", serviceCategoria.buscarTodas());
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

    }
    /*
     * forma 1
     * 
     * @PostMapping("/save")
     * public String guardarVacante(@RequestParam("nombre") String
     * nombre, @RequestParam("descripcion") String descripcion,
     * 
     * @RequestParam("estatus") String estatus, @RequestParam("fecha") String
     * fecha, @RequestParam("destacado") int destacado,
     * 
     * @RequestParam("salario") double salario, @RequestParam("detalles") String
     * detalles ){
     * System.out.println("Nombre Vacante:" + nombre);
     * System.out.println("Descripcion:" + descripcion);
     * System.out.println("Status:" + estatus);
     * System.out.println("Fecha:" + fecha);
     * System.out.println("Destacado:" + destacado);
     * System.out.println("Salario:" + salario);
     * System.out.println("Detalles:" + detalles);
     * return "vacantes/listVacantes";
     * }
     */

}
