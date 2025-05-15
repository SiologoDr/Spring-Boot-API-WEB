package cl.api.buho.demo.web;

import cl.api.buho.demo.models.Personas;
import cl.api.buho.demo.services.PersonasService;
import cl.api.buho.demo.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/personas")
public class WebPersonasController {

    @Autowired
    private PersonasService personasService;

    @Autowired
    private UsuariosService usuariosService;

    // Listar todas
    @GetMapping
    public String listarPersonas(Model model) {
        List<Personas> personas = personasService.listarPersonas();
        model.addAttribute("personas", personas);
        return "web-persona/listar";
    }

    // Buscar por ID
    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Long id, Model model) {
        Optional<Personas> persona = personasService.obtenerPersonaPorId(id);
        persona.ifPresent(p -> model.addAttribute("personas", List.of(p)));
        return "web-persona/listar";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id) {
        personasService.eliminarPersona(id);
        return "redirect:/personas";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarPersona(@PathVariable Long id, Model model) {
        Optional<Personas> persona = personasService.obtenerPersonaPorId(id);
        if (persona.isPresent()) {
            model.addAttribute("personaObj", persona.get());
            model.addAttribute("usuariosLista", usuariosService.listarUsuarios());
            return "web-persona/editar";
        }
        return "redirect:/personas";
    }

    // Procesar edición
    @PostMapping("/editar/{id}")
    public String actualizarPersona(@PathVariable Long id, @ModelAttribute("personaObj") Personas persona) {
        personasService.actualizarPersona(id, persona);
        return "redirect:/personas";
    }

    // Mostrar formulario para crear nueva persona
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("personaObj", new Personas());
        model.addAttribute("usuariosLista", usuariosService.listarUsuarios());
        return "web-persona/nuevo";
    }

    // Guardar nueva persona
    @PostMapping("/guardar")
    public String guardarNuevaPersona(@ModelAttribute("personaObj") Personas persona) {
        personasService.crearPersona(persona);
        return "redirect:/personas";
    }
}
