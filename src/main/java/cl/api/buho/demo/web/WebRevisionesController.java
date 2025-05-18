package cl.api.buho.demo.web;

import cl.api.buho.demo.models.Revisiones;
import cl.api.buho.demo.services.RevisionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/revisiones")
public class WebRevisionesController {

    @Autowired
    private RevisionesService revisionesService;

    // Listar todos
    @GetMapping
    public String listarRevisiones(Model model) {
        List<Revisiones> revisiones = revisionesService.listarRevisiones();
        model.addAttribute("revisiones", revisiones);
        return "web-revision/listar";
    }

    // Buscar por ID
    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Long id, Model model) {
        Optional<Revisiones> revision = revisionesService.obtenerRevisionPorId(id);
        revision.ifPresent(r -> model.addAttribute("revisiones", List.of(r)));
        return "web-revision/listar";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarRevision(@PathVariable Long id) {
        revisionesService.eliminarRevision(id);
        return "redirect:/revisiones";
    }

    // Formulario de edición
    @GetMapping("/editar/{id}")
    public String editarRevision(@PathVariable Long id, Model model) {
        Optional<Revisiones> revision = revisionesService.obtenerRevisionPorId(id);
        if (revision.isPresent()) {
            model.addAttribute("revisionObj", revision.get());
            return "web-revision/editar";
        }
        return "redirect:/revisiones";
    }

    // Procesar edición
    @PostMapping("/editar/{id}")
    public String actualizarRevision(@PathVariable Long id, @ModelAttribute("revisionObj") Revisiones revision) {
        revisionesService.actualizarRevision(id, revision);
        return "redirect:/revisiones";
    }

    // Formulario nuevo
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("revisionObj", new Revisiones());
        return "web-revision/nuevo";
    }

    // Guardar nuevo
    @PostMapping("/guardar")
    public String guardarNuevaRevision(@ModelAttribute("revisionObj") Revisiones revision) {
        revisionesService.crearRevision(revision);
        return "redirect:/revisiones";
    }
}
