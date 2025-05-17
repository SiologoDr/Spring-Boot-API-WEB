package cl.api.buho.demo.web;

import cl.api.buho.demo.models.Desarrolladores;
import cl.api.buho.demo.models.Personas;
import cl.api.buho.demo.services.DesarrolladoresService;
import cl.api.buho.demo.services.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/desarrolladores")
public class WebDesarrolladoresController {

    @Autowired
    private DesarrolladoresService desarrolladoresService;

    @Autowired
    private PersonasService personasService;

    // Listar todos los desarrolladores
    @GetMapping
    public String listarDesarrolladores(Model model) {
        List<Desarrolladores> desarrolladores = desarrolladoresService.listarDesarrolladores();
        model.addAttribute("desarrolladores", desarrolladores);
        return "web-desarrollador/listar";
    }

    // Buscar desarrollador por ID
    @GetMapping("/buscar")
    public String buscarDesarrolladorPorId(@RequestParam("id") Long id, Model model) {
        Optional<Desarrolladores> desarrollador = desarrolladoresService.obtenerDesarrolladorPorId(id);
        desarrollador.ifPresent(d -> model.addAttribute("desarrolladores", List.of(d)));
        return "web-desarrollador/listar";
    }

    // Eliminar desarrollador
    @GetMapping("/eliminar/{id}")
    public String eliminarDesarrollador(@PathVariable Long id) {
        desarrolladoresService.eliminarDesarrollador(id);
        return "redirect:/desarrolladores";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarDesarrollador(@PathVariable Long id, Model model) {
        Optional<Desarrolladores> desarrollador = desarrolladoresService.obtenerDesarrolladorPorId(id);
        if (desarrollador.isPresent()) {
            model.addAttribute("desarrolladorObj", desarrollador.get());

            List<Personas> personasFiltradas = personasService.listarPersonas()
                    .stream()
                    .filter(p -> p.getUsuario() != null &&
                            p.getUsuario().getRol() != null &&
                            "DESARROLLADOR".equalsIgnoreCase(p.getUsuario().getRol().getRol()))
                    .collect(Collectors.toList());

            model.addAttribute("personasLista", personasFiltradas);
            return "web-desarrollador/editar";
        }
        return "redirect:/desarrolladores";
    }

    // Procesar edición
    @PostMapping("/editar/{id}")
    public String actualizarDesarrollador(@PathVariable Long id, @ModelAttribute("desarrolladorObj") Desarrolladores desarrollador) {
        desarrolladoresService.actualizarDesarrollador(id, desarrollador);
        return "redirect:/desarrolladores";
    }

    // Mostrar formulario para crear nuevo desarrollador
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("desarrolladorObj", new Desarrolladores());

        List<Personas> personasFiltradas = personasService.listarPersonas()
                .stream()
                .filter(p -> p.getUsuario() != null &&
                        p.getUsuario().getRol() != null &&
                        "DESARROLLADOR".equalsIgnoreCase(p.getUsuario().getRol().getRol()))
                .collect(Collectors.toList());

        model.addAttribute("personasLista", personasFiltradas);
        return "web-desarrollador/nuevo";
    }

    // Guardar nuevo desarrollador
    @PostMapping("/guardar")
    public String guardarNuevoDesarrollador(@ModelAttribute("desarrolladorObj") Desarrolladores desarrollador) {
        desarrolladoresService.crearDesarrollador(desarrollador);
        return "redirect:/desarrolladores";
    }
}
