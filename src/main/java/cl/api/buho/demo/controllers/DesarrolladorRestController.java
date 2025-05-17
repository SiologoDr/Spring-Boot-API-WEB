package cl.api.buho.demo.controllers;

import cl.api.buho.demo.models.Desarrolladores;
import cl.api.buho.demo.services.DesarrolladoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/desarrolladores")
public class DesarrolladorRestController {

    @Autowired
    private DesarrolladoresService desarrolladoresService;

    // Crear
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<Desarrolladores> crearDesarrollador(@RequestBody Desarrolladores desarrollador) {
        Desarrolladores nuevoDesarrollador = desarrolladoresService.crearDesarrollador(desarrollador);
        return ResponseEntity.ok(nuevoDesarrollador);
    }

    // Obtener todos
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<List<Desarrolladores>> listarDesarrolladores() {
        List<Desarrolladores> desarrolladores = desarrolladoresService.listarDesarrolladores();
        return ResponseEntity.ok(desarrolladores);
    }

    // Obtener por ID
    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Desarrolladores> obtenerDesarrolladorPorId(@PathVariable Long id) {
        Optional<Desarrolladores> desarrollador = desarrolladoresService.obtenerDesarrolladorPorId(id);
        return desarrollador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar
    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Desarrolladores> actualizarDesarrollador(@PathVariable Long id, @RequestBody Desarrolladores desarrolladorActualizado) {
        Optional<Desarrolladores> actualizado = desarrolladoresService.actualizarDesarrollador(id, desarrolladorActualizado);
        return actualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Void> eliminarDesarrollador(@PathVariable Long id) {
        boolean eliminado = desarrolladoresService.eliminarDesarrollador(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
