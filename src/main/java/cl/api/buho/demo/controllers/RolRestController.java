package cl.api.buho.demo.controllers;

import cl.api.buho.demo.models.Roles;
import cl.api.buho.demo.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolRestController {
    @Autowired
    private RolesService rolesService;

    // Crear
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<Roles> crearRol(@RequestBody Roles rol) {
        Roles nuevoRol = rolesService.crearRol(rol);
        return ResponseEntity.ok(nuevoRol);
    }

    // Obtener todos
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<List<Roles>> listarRoles() {
        List<Roles> roles = rolesService.listarRoles();
        return ResponseEntity.ok(roles);
    }

    // Obtener por ID
    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Roles> obtenerRolPorId(@PathVariable Long id) {
        Optional<Roles> rol = rolesService.obtenerRolPorId(id);
        return rol.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar
    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Roles> actualizarRol(@PathVariable Long id, @RequestBody Roles rolActualizado) {
        Optional<Roles> actualizado = rolesService.actualizarRol(id, rolActualizado);
        return actualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        boolean eliminado = rolesService.eliminarRol(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
