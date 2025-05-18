package cl.api.buho.demo.controllers;

import cl.api.buho.demo.models.Estados;
import cl.api.buho.demo.services.EstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estados")
public class EstadoRestController {

    @Autowired
    private EstadosService estadosService;

    // Crear
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<Estados> crearEstado(@RequestBody Estados estado) {
        Estados nuevoEstado = estadosService.crearEstado(estado);
        return ResponseEntity.ok(nuevoEstado);
    }

    // Obtener todos
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<List<Estados>> listarEstados() {
        List<Estados> estados = estadosService.listarEstados();
        return ResponseEntity.ok(estados);
    }

    // Obtener por ID
    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Estados> obtenerEstadoPorId(@PathVariable Long id) {
        Optional<Estados> estado = estadosService.obtenerEstadoPorId(id);
        return estado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar
    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Estados> actualizarEstado(@PathVariable Long id, @RequestBody Estados estadoActualizado) {
        Optional<Estados> actualizado = estadosService.actualizarEstado(id, estadoActualizado);
        return actualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Long id) {
        boolean eliminado = estadosService.eliminarEstado(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
