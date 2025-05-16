package cl.api.buho.demo.controllers;

import cl.api.buho.demo.models.Tecnicos;
import cl.api.buho.demo.services.TecnicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tecnicos")
public class TecnicoRestController {

    @Autowired
    private TecnicosService tecnicosService;

    // Crear
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<Tecnicos> crearTecnico(@RequestBody Tecnicos tecnico) {
        Tecnicos nuevoTecnico = tecnicosService.crearTecnico(tecnico);
        return ResponseEntity.ok(nuevoTecnico);
    }

    // Obtener todos
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<List<Tecnicos>> listarTecnicos() {
        List<Tecnicos> tecnicos = tecnicosService.listarTecnicos();
        return ResponseEntity.ok(tecnicos);
    }

    // Obtener por ID
    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Tecnicos> obtenerTecnicoPorId(@PathVariable Long id) {
        Optional<Tecnicos> tecnico = tecnicosService.obtenerTecnicoPorId(id);
        return tecnico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar
    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Tecnicos> actualizarTecnico(@PathVariable Long id, @RequestBody Tecnicos tecnicoActualizado) {
        Optional<Tecnicos> actualizado = tecnicosService.actualizarTecnico(id, tecnicoActualizado);
        return actualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Void> eliminarTecnico(@PathVariable Long id) {
        boolean eliminado = tecnicosService.eliminarTecnico(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}