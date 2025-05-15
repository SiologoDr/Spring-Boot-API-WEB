package cl.api.buho.demo.web;

import cl.api.buho.demo.models.Clientes;
import cl.api.buho.demo.models.Personas;
import cl.api.buho.demo.services.ClientesService;
import cl.api.buho.demo.services.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clientes")
public class WebClientesController {

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private PersonasService personasService;

    // Listar todos los clientes
    @GetMapping
    public String listarClientes(Model model) {
        List<Clientes> clientes = clientesService.listarClientes();
        model.addAttribute("clientes", clientes);
        return "web-cliente/listar";
    }

    // Buscar cliente por ID
    @GetMapping("/buscar")
    public String buscarClientePorId(@RequestParam("id") Long id, Model model) {
        Optional<Clientes> cliente = clientesService.obtenerClientePorId(id);
        cliente.ifPresent(c -> model.addAttribute("clientes", List.of(c)));
        return "web-cliente/listar";
    }

    // Eliminar cliente
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clientesService.eliminarCliente(id);
        return "redirect:/clientes";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Optional<Clientes> cliente = clientesService.obtenerClientePorId(id);
        if (cliente.isPresent()) {
            model.addAttribute("clienteObj", cliente.get());
            List<Personas> personasFiltradas = personasService.listarPersonas()
                    .stream()
                    .filter(p -> p.getUsuario() != null &&
                            p.getUsuario().getRol() != null &&
                            "CLIENTE".equalsIgnoreCase(p.getUsuario().getRol().getRol()))
                    .collect(Collectors.toList());

            model.addAttribute("personasLista", personasFiltradas);
            return "web-cliente/editar";
        }
        return "redirect:/clientes";
    }

    // Procesar edición
    @PostMapping("/editar/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute("clienteObj") Clientes cliente) {
        clientesService.actualizarCliente(id, cliente);
        return "redirect:/clientes";
    }

    // Mostrar formulario para crear nuevo cliente
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("clienteObj", new Clientes());
        List<Personas> personasFiltradas = personasService.listarPersonas()
                .stream()
                .filter(p -> p.getUsuario() != null &&
                        p.getUsuario().getRol() != null &&
                        "CLIENTE".equalsIgnoreCase(p.getUsuario().getRol().getRol()))
                .collect(Collectors.toList());

        model.addAttribute("personasLista", personasFiltradas);
        return "web-cliente/nuevo";
    }

    // Guardar nuevo cliente
    @PostMapping("/guardar")
    public String guardarNuevoCliente(@ModelAttribute("clienteObj") Clientes cliente) {
        clientesService.crearCliente(cliente);
        return "redirect:/clientes";
    }
}

