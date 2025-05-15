package cl.api.buho.demo.controllers;

import cl.api.buho.demo.models.Clientes;
import cl.api.buho.demo.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

    @Autowired
    private ClientesService clientesService;

    // Crear
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<Clientes> crearCliente(@RequestBody Clientes cliente) {
        Clientes nuevoCliente = clientesService.crearCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    // Obtener todos
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<List<Clientes>> listarClientes() {
        List<Clientes> clientes = clientesService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    // Obtener por ID
    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Clientes> obtenerClientePorId(@PathVariable Long id) {
        Optional<Clientes> cliente = clientesService.obtenerClientePorId(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar
    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Clientes> actualizarCliente(@PathVariable Long id, @RequestBody Clientes clienteActualizado) {
        Optional<Clientes> actualizado = clientesService.actualizarCliente(id, clienteActualizado);
        return actualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        boolean eliminado = clientesService.eliminarCliente(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
