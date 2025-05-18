package cl.api.buho.demo.controllers;

import cl.api.buho.demo.models.NotificacionesClientes;
import cl.api.buho.demo.services.NotificacionesClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notificaciones-clientes")
public class NotificacionClienteRestController {

    @Autowired
    private NotificacionesClientesService notificacionesClientesService;

    // Crear
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<NotificacionesClientes> crearNotificacionCliente(@RequestBody NotificacionesClientes notificacionCliente) {
        NotificacionesClientes nuevaNotificacion = notificacionesClientesService.crearNotificacionCliente(notificacionCliente);
        return ResponseEntity.ok(nuevaNotificacion);
    }

    // Obtener todos
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<List<NotificacionesClientes>> listarNotificacionesClientes() {
        List<NotificacionesClientes> notificaciones = notificacionesClientesService.listarNotificacionesClientes();
        return ResponseEntity.ok(notificaciones);
    }

    // Obtener por ID
    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<NotificacionesClientes> obtenerNotificacionClientePorId(@PathVariable Long id) {
        Optional<NotificacionesClientes> notificacion = notificacionesClientesService.obtenerNotificacionClientePorId(id);
        return notificacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar
    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<NotificacionesClientes> actualizarNotificacionCliente(@PathVariable Long id, @RequestBody NotificacionesClientes notificacionActualizada) {
        Optional<NotificacionesClientes> actualizada = notificacionesClientesService.actualizarNotificacionCliente(id, notificacionActualizada);
        return actualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Void> eliminarNotificacionCliente(@PathVariable Long id) {
        boolean eliminada = notificacionesClientesService.eliminarNotificacionCliente(id);
        return eliminada ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
