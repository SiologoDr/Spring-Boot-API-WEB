package cl.api.buho.demo.controllers;

import cl.api.buho.demo.models.Asignaciones;
import cl.api.buho.demo.services.AsignacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asignaciones")
public class AsignacionRestController {

    @Autowired
    private AsignacionesService asignacionesService;

    // Crear
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<Asignaciones> crearAsignacion(@RequestBody Asignaciones asignacion) {
        Asignaciones nuevaAsignacion = asignacionesService.crearAsignacion(asignacion);
        return ResponseEntity.ok(nuevaAsignacion);
    }

    // Obtener todas
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<List<Asignaciones>> listarAsignaciones() {
        List<Asignaciones> asignaciones = asignacionesService.listarAsignaciones();
        return ResponseEntity.ok(asignaciones);
    }

    // Obtener por ID
    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Asignaciones> obtenerAsignacionPorId(@PathVariable Long id) {
        Optional<Asignaciones> asignacion = asignacionesService.obtenerAsignacionPorId(id);
        return asignacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar
    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Asignaciones> actualizarAsignacion(@PathVariable Long id, @RequestBody Asignaciones asignacionActualizada) {
        Optional<Asignaciones> actualizada = asignacionesService.actualizarAsignacion(id, asignacionActualizada);
        return actualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Void> eliminarAsignacion(@PathVariable Long id) {
        boolean eliminado = asignacionesService.eliminarAsignacion(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
