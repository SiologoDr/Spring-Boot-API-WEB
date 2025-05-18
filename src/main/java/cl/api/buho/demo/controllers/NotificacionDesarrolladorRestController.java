package cl.api.buho.demo.controllers;

import cl.api.buho.demo.models.NotificacionesDesarrolladores;
import cl.api.buho.demo.services.NotificacionesDesarrolladoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notificaciones-desarrolladores")
public class NotificacionDesarrolladorRestController {

    @Autowired
    private NotificacionesDesarrolladoresService notificacionesDesarrolladoresService;

    // Crear
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<NotificacionesDesarrolladores> crearNotificacionDesarrollador(@RequestBody NotificacionesDesarrolladores notificacion) {
        NotificacionesDesarrolladores nuevaNotificacion = notificacionesDesarrolladoresService.crearNotificacionDesarrollador(notificacion);
        return ResponseEntity.ok(nuevaNotificacion);
    }

    // Obtener todos
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<List<NotificacionesDesarrolladores>> listarNotificacionesDesarrolladores() {
        List<NotificacionesDesarrolladores> notificaciones = notificacionesDesarrolladoresService.listarNotificacionesDesarrolladores();
        return ResponseEntity.ok(notificaciones);
    }

    // Obtener por ID
    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<NotificacionesDesarrolladores> obtenerNotificacionDesarrolladorPorId(@PathVariable Long id) {
        Optional<NotificacionesDesarrolladores> notificacion = notificacionesDesarrolladoresService.obtenerNotificacionDesarrolladorPorId(id);
        return notificacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar
    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<NotificacionesDesarrolladores> actualizarNotificacionDesarrollador(@PathVariable Long id, @RequestBody NotificacionesDesarrolladores notificacionActualizada) {
        Optional<NotificacionesDesarrolladores> actualizada = notificacionesDesarrolladoresService.actualizarNotificacionDesarrollador(id, notificacionActualizada);
        return actualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Void> eliminarNotificacionDesarrollador(@PathVariable Long id) {
        boolean eliminada = notificacionesDesarrolladoresService.eliminarNotificacionDesarrollador(id);
        return eliminada ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
