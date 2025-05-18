package cl.api.buho.demo.controllers;

import cl.api.buho.demo.models.Revisiones;
import cl.api.buho.demo.services.RevisionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/revisiones")
public class RevisionRestController {

    @Autowired
    private RevisionesService revisionesService;

    // Crear
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<Revisiones> crearRevision(@RequestBody Revisiones revision) {
        Revisiones nuevaRevision = revisionesService.crearRevision(revision);
        return ResponseEntity.ok(nuevaRevision);
    }

    // Obtener todos
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<List<Revisiones>> listarRevisiones() {
        List<Revisiones> revisiones = revisionesService.listarRevisiones();
        return ResponseEntity.ok(revisiones);
    }

    // Obtener por ID
    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Revisiones> obtenerRevisionPorId(@PathVariable Long id) {
        Optional<Revisiones> revision = revisionesService.obtenerRevisionPorId(id);
        return revision.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar
    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Revisiones> actualizarRevision(@PathVariable Long id, @RequestBody Revisiones revisionActualizada) {
        Optional<Revisiones> actualizada = revisionesService.actualizarRevision(id, revisionActualizada);
        return actualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Void> eliminarRevision(@PathVariable Long id) {
        boolean eliminado = revisionesService.eliminarRevision(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
