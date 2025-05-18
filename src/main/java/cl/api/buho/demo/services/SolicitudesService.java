package cl.api.buho.demo.services;

import cl.api.buho.demo.models.Solicitudes;
import cl.api.buho.demo.repositories.ISolicitudesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudesService {

    @Autowired
    private ISolicitudesRepository solicitudesRepository;

    // Crear
    public Solicitudes crearSolicitud(Solicitudes solicitud) {
        return solicitudesRepository.save(solicitud);
    }

    // Listar todos
    public List<Solicitudes> listarSolicitudes() {
        return solicitudesRepository.findAll();
    }

    // Buscar por ID
    public Optional<Solicitudes> obtenerSolicitudPorId(Long id) {
        return solicitudesRepository.findById(id);
    }

    // Actualizar
    public Optional<Solicitudes> actualizarSolicitud(Long id, Solicitudes solicitudActualizada) {
        return solicitudesRepository.findById(id).map(solicitud -> {
            solicitud.setDescripcion(solicitudActualizada.getDescripcion());
            solicitud.setObservacion(solicitudActualizada.getObservacion());
            solicitud.setPrioridad(solicitudActualizada.getPrioridad());
            solicitud.setCliente(solicitudActualizada.getCliente());
            solicitud.setTecnico(solicitudActualizada.getTecnico());
            solicitud.setEstado(solicitudActualizada.getEstado());
            solicitud.setRevision(solicitudActualizada.getRevision());
            return solicitudesRepository.save(solicitud);
        });
    }

    // Eliminar
    public boolean eliminarSolicitud(Long id) {
        if (solicitudesRepository.existsById(id)) {
            solicitudesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

