package cl.api.buho.demo.web;

import cl.api.buho.demo.models.Estados;
import cl.api.buho.demo.services.EstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/estados")
public class WebEstadosController {

    @Autowired
    private EstadosService estadosService;

    // Listar todos
    @GetMapping
    public String listarEstados(Model model) {
        List<Estados> estados = estadosService.listarEstados();
        model.addAttribute("estados", estados);
        return "web-estado/listar";
    }

    // Buscar por ID
    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Long id, Model model) {
        Optional<Estados> estado = estadosService.obtenerEstadoPorId(id);
        estado.ifPresent(e -> model.addAttribute("estados", List.of(e)));
        return "web-estado/listar";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarEstado(@PathVariable Long id) {
        estadosService.eliminarEstado(id);
        return "redirect:/estados";
    }

    // Formulario de edición
    @GetMapping("/editar/{id}")
    public String editarEstado(@PathVariable Long id, Model model) {
        Optional<Estados> estado = estadosService.obtenerEstadoPorId(id);
        if (estado.isPresent()) {
            model.addAttribute("estadoObj", estado.get());
            return "web-estado/editar";
        }
        return "redirect:/estados";
    }

    // Procesar edición
    @PostMapping("/editar/{id}")
    public String actualizarEstado(@PathVariable Long id, @ModelAttribute("estadoObj") Estados estado) {
        estadosService.actualizarEstado(id, estado);
        return "redirect:/estados";
    }

    // Formulario nuevo
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("estadoObj", new Estados());
        return "web-estado/nuevo";
    }

    // Guardar nuevo
    @PostMapping("/guardar")
    public String guardarNuevoEstado(@ModelAttribute("estadoObj") Estados estado) {
        estadosService.crearEstado(estado);
        return "redirect:/estados";
    }
}