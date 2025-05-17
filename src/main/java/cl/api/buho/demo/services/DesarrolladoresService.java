package cl.api.buho.demo.services;

import cl.api.buho.demo.models.Desarrolladores;
import cl.api.buho.demo.repositories.IDesarrolladoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesarrolladoresService {

    @Autowired
    private IDesarrolladoresRepository desarrolladoresRepository;

    // Crear
    public Desarrolladores crearDesarrollador(Desarrolladores desarrollador) {
        return desarrolladoresRepository.save(desarrollador);
    }

    // Listar todos
    public List<Desarrolladores> listarDesarrolladores() {
        return desarrolladoresRepository.findAll();
    }

    // Buscar por ID
    public Optional<Desarrolladores> obtenerDesarrolladorPorId(Long id) {
        return desarrolladoresRepository.findById(id);
    }

    // Actualizar
    public Optional<Desarrolladores> actualizarDesarrollador(Long id, Desarrolladores desarrolladorActualizado) {
        return desarrolladoresRepository.findById(id).map(desarrollador -> {
            desarrollador.setArea(desarrolladorActualizado.getArea());
            desarrollador.setPersona(desarrolladorActualizado.getPersona());
            return desarrolladoresRepository.save(desarrollador);
        });
    }

    // Eliminar
    public boolean eliminarDesarrollador(Long id) {
        if (desarrolladoresRepository.existsById(id)) {
            desarrolladoresRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

