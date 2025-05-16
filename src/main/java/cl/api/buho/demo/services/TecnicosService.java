package cl.api.buho.demo.services;

import cl.api.buho.demo.models.Tecnicos;
import cl.api.buho.demo.repositories.ITecnicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicosService {

    @Autowired
    private ITecnicosRepository tecnicosRepository;

    // Crear
    public Tecnicos crearTecnico(Tecnicos tecnico) {
        return tecnicosRepository.save(tecnico);
    }

    // Listar todos
    public List<Tecnicos> listarTecnicos() {
        return tecnicosRepository.findAll();
    }

    // Buscar por ID
    public Optional<Tecnicos> obtenerTecnicoPorId(Long id) {
        return tecnicosRepository.findById(id);
    }

    // Actualizar
    public Optional<Tecnicos> actualizarTecnico(Long id, Tecnicos tecnicoActualizado) {
        return tecnicosRepository.findById(id).map(tecnico -> {
            tecnico.setEspecialidad(tecnicoActualizado.getEspecialidad());
            tecnico.setPersona(tecnicoActualizado.getPersona());
            return tecnicosRepository.save(tecnico);
        });
    }

    // Eliminar
    public boolean eliminarTecnico(Long id) {
        if (tecnicosRepository.existsById(id)) {
            tecnicosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

