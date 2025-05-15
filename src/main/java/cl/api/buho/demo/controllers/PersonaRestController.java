package cl.api.buho.demo.controllers;

import cl.api.buho.demo.models.Personas;
import cl.api.buho.demo.services.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
public class PersonaRestController {

    @Autowired
    private PersonasService personasService;

    // Crear
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<Personas> crearPersona(@RequestBody Personas persona) {
        Personas nuevaPersona = personasService.crearPersona(persona);
        return ResponseEntity.ok(nuevaPersona);
    }

    // Obtener todos
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<List<Personas>> listarPersonas() {
        List<Personas> personas = personasService.listarPersonas();
        return ResponseEntity.ok(personas);
    }

    // Obtener por ID
    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Personas> obtenerPersonaPorId(@PathVariable Long id) {
        Optional<Personas> persona = personasService.obtenerPersonaPorId(id);
        return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar
    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Personas> actualizarPersona(@PathVariable Long id, @RequestBody Personas personaActualizada) {
        Optional<Personas> actualizada = personasService.actualizarPersona(id, personaActualizada);
        return actualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        boolean eliminada = personasService.eliminarPersona(id);
        return eliminada ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
