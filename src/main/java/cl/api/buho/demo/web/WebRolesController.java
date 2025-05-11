package cl.api.buho.demo.web;

import cl.api.buho.demo.models.Roles;
import cl.api.buho.demo.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/roles")
public class WebRolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping
    public String listarRoles(Model model) {
        List<Roles> roles = rolesService.listarRoles();
        model.addAttribute("roles", roles);
        return "web-rol/listar";
    }

    // Buscar por ID
    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Long id, Model model) {
        Optional<Roles> rol = rolesService.obtenerRolPorId(id);
        rol.ifPresent(r -> model.addAttribute("roles", List.of(r)));
        return "web-rol/listar";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarRol(@PathVariable Long id) {
        rolesService.eliminarRol(id);
        return "redirect:/roles";
    }

    // Redirige a formulario de edición
    @GetMapping("/editar/{id}")
    public String editarRol(@PathVariable Long id, Model model) {
        Optional<Roles> rol = rolesService.obtenerRolPorId(id);
        if (rol.isPresent()) {
            model.addAttribute("rolObj", rol.get());
            return "web-rol/editar";
        }
        return "redirect:/roles";
    }

    // Procesar edición
    @PostMapping("/editar/{id}")
    public String actualizarRol(@PathVariable Long id, @ModelAttribute("rolObj") Roles rol) {
        rolesService.actualizarRol(id, rol);
        return "redirect:/roles";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("rolObj", new Roles());
        return "web-rol/nuevo";
    }

    @PostMapping("/guardar")
    public String guardarNuevoRol(@ModelAttribute("rolObj") Roles rol) {
        rolesService.crearRol(rol);
        return "redirect:/roles";
    }
}

