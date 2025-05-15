package cl.api.buho.demo.services;

import cl.api.buho.demo.models.Personas;
import cl.api.buho.demo.repositories.IPersonasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonasService {

    @Autowired
    private IPersonasRepository personasRepository;

    // Crear
    public Personas crearPersona(Personas persona) {
        return personasRepository.save(persona);
    }

    // Listar todos
    public List<Personas> listarPersonas() {
        return personasRepository.findAll();
    }

    // Buscar por ID
    public Optional<Personas> obtenerPersonaPorId(Long id) {
        return personasRepository.findById(id);
    }

    // Actualizar
    public Optional<Personas> actualizarPersona(Long id, Personas personaActualizada) {
        return personasRepository.findById(id).map(persona -> {
            persona.setNombre(personaActualizada.getNombre());
            persona.setCorreo(personaActualizada.getCorreo());
            persona.setTelefono(personaActualizada.getTelefono());
            persona.setUsuario(personaActualizada.getUsuario());
            return personasRepository.save(persona);
        });
    }

    // Eliminar
    public boolean eliminarPersona(Long id) {
        if (personasRepository.existsById(id)) {
            personasRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

