package com.organizacionname.AppSB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.organizacionname.AppSB.Models.SolicitudesModels;
import com.organizacionname.AppSB.Models.UsuariosModels;
import com.organizacionname.AppSB.Models.VacantesModels;
import com.organizacionname.AppSB.Services.ISolicitudesServices;
import com.organizacionname.AppSB.Services.IUsuariosServices;
import com.organizacionname.AppSB.Services.IVacantesService;
import com.organizacionname.AppSB.util.Utileria;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudesControllers {

    @Autowired
    private IVacantesService serVac;

    @Autowired
    private IUsuariosServices serUsu;

    @Autowired
    private ISolicitudesServices servSol;

    @Value("${AppSB.ruta.cv}")
    private String rutaCv;

    @GetMapping("/create/{idvacante}")
    public String crear(SolicitudesModels solicitudesModels, @PathVariable("idvacante") Integer idVacante,
            Model model) {
        VacantesModels vacantesModels = serVac.buscarPorId(idVacante);
        System.out.println("idvacante es:" + idVacante);
        model.addAttribute("vacante", vacantesModels);
        return "solicitudes/formSolicitud";
    }

    @PostMapping("/save")
    public String guardarSolicitud(SolicitudesModels solicitudesModels, BindingResult result,
        @RequestParam("archivoCV") MultipartFile multipart, Authentication auth, RedirectAttributes  attributes) {
            
        String  usuarioActual = auth.getName();

        if (result.hasErrors()) {
            System.out.println("Existen errores");
            return "solicitudes/formSolicitud";
        }
        if (! multipart.isEmpty()) {
            String nombreArchivo = Utileria.guardarArchivo(multipart, rutaCv);
            if (nombreArchivo !=null) {
                solicitudesModels.setArchivo(nombreArchivo);
            }
        }

        UsuariosModels usuario = serUsu.buscarPorUsername(usuarioActual);
        solicitudesModels.setUsuario(usuario);

        servSol.guardar(solicitudesModels);
        attributes.addFlashAttribute("msg", "Postulacion realizada con exito");

        System.out.println("solicitud: " + solicitudesModels);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String eliminarSolicitud(@PathVariable("id") int idSolicitud, RedirectAttributes attributes) {
        System.out.println("Borrando solicitud con id:" + idSolicitud);
        servSol.eliminar(idSolicitud);
        attributes.addFlashAttribute("msg", "Registro Eliminado");
        return "redirect:/solicitudes/indexPaginate";
    }

    @GetMapping("/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page){
        Page<SolicitudesModels>  listaSolicitudes = servSol.buscarTodas(page);
        model.addAttribute("solicitudes", listaSolicitudes);
        return "solicitudes/listSolicitudes";
    }
    

}
