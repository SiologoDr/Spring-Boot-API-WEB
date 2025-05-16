package cl.api.buho.demo.web;

import cl.api.buho.demo.models.Personas;
import cl.api.buho.demo.models.Tecnicos;
import cl.api.buho.demo.services.PersonasService;
import cl.api.buho.demo.services.TecnicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tecnicos")
public class WebTecnicosController {

    @Autowired
    private TecnicosService tecnicosService;

    @Autowired
    private PersonasService personasService;

    // Listar todos los técnicos
    @GetMapping
    public String listarTecnicos(Model model) {
        List<Tecnicos> tecnicos = tecnicosService.listarTecnicos();
        model.addAttribute("tecnicos", tecnicos);
        return "web-tecnico/listar";
    }

    // Buscar técnico por ID
    @GetMapping("/buscar")
    public String buscarTecnicoPorId(@RequestParam("id") Long id, Model model) {
        Optional<Tecnicos> tecnico = tecnicosService.obtenerTecnicoPorId(id);
        tecnico.ifPresent(t -> model.addAttribute("tecnicos", List.of(t)));
        return "web-tecnico/listar";
    }

    // Eliminar técnico
    @GetMapping("/eliminar/{id}")
    public String eliminarTecnico(@PathVariable Long id) {
        tecnicosService.eliminarTecnico(id);
        return "redirect:/tecnicos";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarTecnico(@PathVariable Long id, Model model) {
        Optional<Tecnicos> tecnico = tecnicosService.obtenerTecnicoPorId(id);
        if (tecnico.isPresent()) {
            model.addAttribute("tecnicoObj", tecnico.get());

            List<Personas> personasFiltradas = personasService.listarPersonas()
                    .stream()
                    .filter(p -> p.getUsuario() != null &&
                            p.getUsuario().getRol() != null &&
                            "TECNICO".equalsIgnoreCase(p.getUsuario().getRol().getRol()))
                    .collect(Collectors.toList());

            model.addAttribute("personasLista", personasFiltradas);
            return "web-tecnico/editar";
        }
        return "redirect:/tecnicos";
    }

    // Procesar edición
    @PostMapping("/editar/{id}")
    public String actualizarTecnico(@PathVariable Long id, @ModelAttribute("tecnicoObj") Tecnicos tecnico) {
        tecnicosService.actualizarTecnico(id, tecnico);
        return "redirect:/tecnicos";
    }

    // Mostrar formulario para crear nuevo técnico
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("tecnicoObj", new Tecnicos());

        List<Personas> personasFiltradas = personasService.listarPersonas()
                .stream()
                .filter(p -> p.getUsuario() != null &&
                        p.getUsuario().getRol() != null &&
                        "TECNICO".equalsIgnoreCase(p.getUsuario().getRol().getRol()))
                .collect(Collectors.toList());

        model.addAttribute("personasLista", personasFiltradas);
        return "web-tecnico/nuevo";
    }

    // Guardar nuevo técnico
    @PostMapping("/guardar")
    public String guardarNuevoTecnico(@ModelAttribute("tecnicoObj") Tecnicos tecnico) {
        tecnicosService.crearTecnico(tecnico);
        return "redirect:/tecnicos";
    }
}

