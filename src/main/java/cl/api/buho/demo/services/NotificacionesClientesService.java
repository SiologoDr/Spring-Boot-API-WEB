package cl.api.buho.demo.services;

import cl.api.buho.demo.models.NotificacionesClientes;
import cl.api.buho.demo.repositories.INotificacionesClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionesClientesService {

    @Autowired
    private INotificacionesClientesRepository notificacionesClientesRepository;

    // Crear
    public NotificacionesClientes crearNotificacionCliente(NotificacionesClientes notificacionCliente) {
        return notificacionesClientesRepository.save(notificacionCliente);
    }

    // Listar todos
    public List<NotificacionesClientes> listarNotificacionesClientes() {
        return notificacionesClientesRepository.findAll();
    }

    // Buscar por ID
    public Optional<NotificacionesClientes> obtenerNotificacionClientePorId(Long id) {
        return notificacionesClientesRepository.findById(id);
    }

    // Actualizar
    public Optional<NotificacionesClientes> actualizarNotificacionCliente(Long id, NotificacionesClientes notificacionActualizada) {
        return notificacionesClientesRepository.findById(id).map(notificacion -> {
            notificacion.setObservacion(notificacionActualizada.getObservacion());
            notificacion.setSolicitud(notificacionActualizada.getSolicitud());
            return notificacionesClientesRepository.save(notificacion);
        });
    }

    // Eliminar
    public boolean eliminarNotificacionCliente(Long id) {
        if (notificacionesClientesRepository.existsById(id)) {
            notificacionesClientesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
