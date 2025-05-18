package cl.api.buho.demo.services;

import cl.api.buho.demo.models.Estados;
import cl.api.buho.demo.repositories.IEstadosRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadosService {

    @Autowired
    private IEstadosRepositoy estadosRepository;

    // Crear
    public Estados crearEstado(Estados estado) {
        return estadosRepository.save(estado);
    }

    // Listar todos
    public List<Estados> listarEstados() {
        return estadosRepository.findAll();
    }

    // Buscar por ID
    public Optional<Estados> obtenerEstadoPorId(Long id) {
        return estadosRepository.findById(id);
    }

    // Actualizar
    public Optional<Estados> actualizarEstado(Long id, Estados estadoActualizado) {
        return estadosRepository.findById(id).map(estado -> {
            estado.setEstado(estadoActualizado.getEstado());
            estado.setDescripcion(estadoActualizado.getDescripcion());
            return estadosRepository.save(estado);
        });
    }

    // Eliminar
    public boolean eliminarEstado(Long id) {
        if (estadosRepository.existsById(id)) {
            estadosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
