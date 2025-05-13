package cl.api.buho.demo.web;

import cl.api.buho.demo.models.Usuarios;
import cl.api.buho.demo.services.RolesService;
import cl.api.buho.demo.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class WebUsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private RolesService rolesService;

    // Listar todos
    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuarios> usuarios = usuariosService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "web-usuario/listar";
    }

    // Buscar por ID
    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Long id, Model model) {
        Optional<Usuarios> usuario = usuariosService.obtenerUsuarioPorId(id);
        usuario.ifPresent(u -> model.addAttribute("usuarios", List.of(u)));
        return "web-usuario/listar";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuariosService.eliminarUsuario(id);
        return "redirect:/usuarios";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Optional<Usuarios> usuario = usuariosService.obtenerUsuarioPorId(id);
        if (usuario.isPresent()) {
            model.addAttribute("usuarioObj", usuario.get());
            model.addAttribute("rolesLista", rolesService.listarRoles());
            return "web-usuario/editar";
        }
        return "redirect:/usuarios";
    }

    // Procesar edición
    @PostMapping("/editar/{id}")
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute("usuarioObj") Usuarios usuario) {
        if (usuario.getContraseña() == null || usuario.getContraseña().isEmpty()) {
            Optional<Usuarios> usuarioExistente = usuariosService.obtenerUsuarioPorId(id);
            if (usuarioExistente.isPresent()) {
                usuario.setContraseña(usuarioExistente.get().getContraseña());
            }
        }
        usuariosService.actualizarUsuario(id, usuario);
        return "redirect:/usuarios";
    }

    // Mostrar formulario para crear nuevo usuario
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuarioObj", new Usuarios());
        model.addAttribute("rolesLista", rolesService.listarRoles());
        return "web-usuario/nuevo";
    }

    // Guardar nuevo usuario
    @PostMapping("/guardar")
    public String guardarNuevoUsuario(@ModelAttribute("usuarioObj") Usuarios usuario) {
        usuariosService.crearUsuario(usuario);
        return "redirect:/usuarios";
    }
}
