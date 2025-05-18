package cl.api.buho.demo.services;

import cl.api.buho.demo.models.NotificacionesDesarrolladores;
import cl.api.buho.demo.repositories.INotificacionesDesarrolladoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionesDesarrolladoresService {

    @Autowired
    private INotificacionesDesarrolladoresRepository notificacionesDesarrolladoresRepository;

    // Crear
    public NotificacionesDesarrolladores crearNotificacionDesarrollador(NotificacionesDesarrolladores notificacion) {
        return notificacionesDesarrolladoresRepository.save(notificacion);
    }

    // Listar todos
    public List<NotificacionesDesarrolladores> listarNotificacionesDesarrolladores() {
        return notificacionesDesarrolladoresRepository.findAll();
    }

    // Buscar por ID
    public Optional<NotificacionesDesarrolladores> obtenerNotificacionDesarrolladorPorId(Long id) {
        return notificacionesDesarrolladoresRepository.findById(id);
    }

    // Actualizar
    public Optional<NotificacionesDesarrolladores> actualizarNotificacionDesarrollador(Long id, NotificacionesDesarrolladores notificacionActualizada) {
        return notificacionesDesarrolladoresRepository.findById(id).map(notificacion -> {
            notificacion.setObservacion(notificacionActualizada.getObservacion());
            notificacion.setAsignacion(notificacionActualizada.getAsignacion());
            return notificacionesDesarrolladoresRepository.save(notificacion);
        });
    }

    // Eliminar
    public boolean eliminarNotificacionDesarrollador(Long id) {
        if (notificacionesDesarrolladoresRepository.existsById(id)) {
            notificacionesDesarrolladoresRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
