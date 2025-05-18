package cl.api.buho.demo.services;

import cl.api.buho.demo.models.Asignaciones;
import cl.api.buho.demo.repositories.IAsignacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignacionesService {

    @Autowired
    private IAsignacionesRepository asignacionesRepository;

    // Crear
    public Asignaciones crearAsignacion(Asignaciones asignacion) {
        return asignacionesRepository.save(asignacion);
    }

    // Listar todas
    public List<Asignaciones> listarAsignaciones() {
        return asignacionesRepository.findAll();
    }

    // Buscar por ID
    public Optional<Asignaciones> obtenerAsignacionPorId(Long id) {
        return asignacionesRepository.findById(id);
    }

    // Actualizar
    public Optional<Asignaciones> actualizarAsignacion(Long id, Asignaciones asignacionActualizada) {
        return asignacionesRepository.findById(id).map(asignacion -> {
            asignacion.setDescripcion(asignacionActualizada.getDescripcion());
            asignacion.setSolicitud(asignacionActualizada.getSolicitud());
            asignacion.setDesarrollador(asignacionActualizada.getDesarrollador());
            return asignacionesRepository.save(asignacion);
        });
    }

    // Eliminar
    public boolean eliminarAsignacion(Long id) {
        if (asignacionesRepository.existsById(id)) {
            asignacionesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
