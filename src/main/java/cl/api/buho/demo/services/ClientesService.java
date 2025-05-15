package cl.api.buho.demo.services;

import cl.api.buho.demo.models.Clientes;
import cl.api.buho.demo.repositories.IClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private IClientesRepository clientesRepository;

    // Crear
    public Clientes crearCliente(Clientes cliente) {
        return clientesRepository.save(cliente);
    }

    // Listar todos
    public List<Clientes> listarClientes() {
        return clientesRepository.findAll();
    }

    // Buscar por ID
    public Optional<Clientes> obtenerClientePorId(Long id) {
        return clientesRepository.findById(id);
    }

    // Actualizar
    public Optional<Clientes> actualizarCliente(Long id, Clientes clienteActualizado) {
        return clientesRepository.findById(id).map(cliente -> {
            cliente.setRuc(clienteActualizado.getRuc());
            cliente.setPersona(clienteActualizado.getPersona());
            return clientesRepository.save(cliente);
        });
    }

    // Eliminar
    public boolean eliminarCliente(Long id) {
        if (clientesRepository.existsById(id)) {
            clientesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
