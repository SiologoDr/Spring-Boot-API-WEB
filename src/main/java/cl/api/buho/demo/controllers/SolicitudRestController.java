package cl.api.buho.demo.controllers;

import cl.api.buho.demo.models.Solicitudes;
import cl.api.buho.demo.services.SolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudRestController {

    @Autowired
    private SolicitudesService solicitudesService;

    // Crear
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<Solicitudes> crearSolicitud(@RequestBody Solicitudes solicitud) {
        Solicitudes nuevaSolicitud = solicitudesService.crearSolicitud(solicitud);
        return ResponseEntity.ok(nuevaSolicitud);
    }

    // Obtener todos
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<List<Solicitudes>> listarSolicitudes() {
        List<Solicitudes> solicitudes = solicitudesService.listarSolicitudes();
        return ResponseEntity.ok(solicitudes);
    }

    // Obtener por ID
    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Solicitudes> obtenerSolicitudPorId(@PathVariable Long id) {
        Optional<Solicitudes> solicitud = solicitudesService.obtenerSolicitudPorId(id);
        return solicitud.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar
    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Solicitudes> actualizarSolicitud(@PathVariable Long id, @RequestBody Solicitudes solicitudActualizada) {
        Optional<Solicitudes> actualizado = solicitudesService.actualizarSolicitud(id, solicitudActualizada);
        return actualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable Long id) {
        boolean eliminado = solicitudesService.eliminarSolicitud(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
