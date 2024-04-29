package com.organizacionname.AppSB.Controllers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.organizacionname.AppSB.Models.*;
import com.organizacionname.AppSB.Services.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeControllers {

    @Autowired
    private ICategoriasService serviceCategoria;

    @Autowired
    private IVacantesService serviceVacantes;

    @Autowired
    private IUsuariosServices serviceUsuarios;

    @Autowired
    private PasswordEncoder passEncoder;

    @GetMapping("/tabla")
    public String mostrarTabla(Model model) {
        List<VacantesModels> lista = serviceVacantes.buscarTodas();
        model.addAttribute("vacantes", lista);

        return "vacantes/tabla";
    }

    @GetMapping("/detalle")
    public String mostrarDetalle(Model model) {
        VacantesModels vacante = new VacantesModels();
        vacante.setNombre("ingeniero Informatico");
        vacante.setDescripcion("Necesitamos con urgencia alguien");
        vacante.setFecha(new Date());
        vacante.setSalario(9700.0);
        model.addAttribute("vacante", vacante);
        return "vacantes/detalle";
    }

    @GetMapping("/listado")
    public String mostrarListado(Model model) {
        List<String> lista = new LinkedList<String>();
        lista.add("Ingeniero de sistemas");
        lista.add("Arquitecto de software");
        lista.add("Vendedor");
        lista.add("Tecnico");

        model.addAttribute("empleos", lista);
        return "vacantes/listado";
    }

    @GetMapping("/")
    public String inicio(Model model) {
        /*
         * model.addAttribute("mensaje", "Bienvenido a empleos app");
         * model.addAttribute("fecha", new Date()); Forma 1
         */
        // forma 2
        /*
         * String nombre ="Programador";
         * Date fechaPub = new Date();
         * double salario = 9000.0;
         * boolean vigente = true;
         * 
         * model.addAttribute("nombre", nombre);
         * model.addAttribute("pubFecha", fechaPub);
         * model.addAttribute("salario", salario);
         * model.addAttribute("vigente", vigente);
         */
        // forma 3
        /*
         * List<VacantesModels> lista = serviceVacantes.buscarTodas();
         * model.addAttribute("vacantes", lista);
         * return "vacantes/home";
         */
        return "vacantes/home";
    }

    @GetMapping("/index")
    public String mostrarlogin(Authentication authentication, HttpSession session) {
        String username = authentication.getName();
        System.out.println("ha ingresado al index: " + username);

        for (GrantedAuthority rol : authentication.getAuthorities()) {
            System.out.println("rol: " + rol.getAuthority());
        }
        if (session.getAttribute("usuario") == null) {
            UsuariosModels usuarioLogeado = serviceUsuarios.buscarPorUsername(username);
            usuarioLogeado.setPassword(null);
            System.out.println("Usuario logeado: " + usuarioLogeado);
            session.setAttribute("usuario", usuarioLogeado);
        }
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String registrarse(UsuariosModels usuarioModels) {
        return "usuarios/formRegistro";
    }

    @PostMapping("/signup")
    public String guardarRegistro(UsuariosModels usuarioModels, RedirectAttributes attributes) {

        String passPlano = usuarioModels.getPassword();// se llama la contraseña plana para poder validarla
        String passEncriptado = passEncoder.encode(passPlano);// Encriptado de la contraseña
        usuarioModels.setPassword(passEncriptado); // Se asigna el password encriptado a la propiedad password del
                                                   // modelo
        usuarioModels.setEstatus(1); // Activado por defecto
        usuarioModels.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor

        // Creamos el Perfil que le asignaremos al usuario nuevo
        PerfilesModels perfil = new PerfilesModels();
        perfil.setId(3); // Perfil USUARIO
        usuarioModels.agregar(perfil);

        /**
         * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
         */
        serviceUsuarios.guardar(usuarioModels);

        attributes.addFlashAttribute("msg", "El registro fue guardado correctamente!");

        return "redirect:/usuarios/index";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "usuarios/formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/login";
    }

    @GetMapping("/bcrypt/{texto}")
    @ResponseBody
    public String generarContrasena(@PathVariable("texto") String texto) {
        return texto + "Encriptado en Bcrypt: " + passEncoder.encode(texto);
    }

    @GetMapping("/search")
    public String buscar(@ModelAttribute("search") VacantesModels vacantesModels, Model model) {
        System.out.println("buscando por:" + vacantesModels);

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion",
                ExampleMatcher.GenericPropertyMatchers.contains());

        Example<VacantesModels> example = Example.of(vacantesModels, matcher);
        List<VacantesModels> listavacante = serviceVacantes.buscarByExample(example);
        model.addAttribute("vacantes", listavacante);
        return "vacantes/home";
    }

    @ModelAttribute
    public void setGenerico(Model model) {
        VacantesModels vacanteSearch = new VacantesModels();
        vacanteSearch.reset();
        model.addAttribute("search", vacanteSearch);
        model.addAttribute("vacantes", serviceVacantes.destacadasVacantes());
        model.addAttribute("categorias", serviceCategoria.buscarTodas());

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

    }
}
