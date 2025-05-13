package cl.api.buho.demo.controllers;

import cl.api.buho.demo.models.Usuarios;
import cl.api.buho.demo.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuariosService usuariosService;

    // Crear
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<Usuarios> crearUsuario(@RequestBody Usuarios usuario) {
        Usuarios nuevoUsuario = usuariosService.crearUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // Obtener todos
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<List<Usuarios>> listarUsuarios() {
        List<Usuarios> usuarios = usuariosService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // Obtener por ID
    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Usuarios> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuarios> usuario = usuariosService.obtenerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar
    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Usuarios> actualizarUsuario(@PathVariable Long id, @RequestBody Usuarios usuarioActualizado) {
        Optional<Usuarios> actualizado = usuariosService.actualizarUsuario(id, usuarioActualizado);
        return actualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usuariosService.eliminarUsuario(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
