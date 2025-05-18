package cl.api.buho.demo.services;

import cl.api.buho.demo.models.Revisiones;
import cl.api.buho.demo.repositories.IRevisionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RevisionesService {

    @Autowired
    private IRevisionesRepository revisionesRepository;

    // Crear
    public Revisiones crearRevision(Revisiones revision) {
        return revisionesRepository.save(revision);
    }

    // Listar todos
    public List<Revisiones> listarRevisiones() {
        return revisionesRepository.findAll();
    }

    // Buscar por ID
    public Optional<Revisiones> obtenerRevisionPorId(Long id) {
        return revisionesRepository.findById(id);
    }

    // Actualizar
    public Optional<Revisiones> actualizarRevision(Long id, Revisiones revisionActualizada) {
        return revisionesRepository.findById(id).map(revision -> {
            revision.setRevision(revisionActualizada.getRevision());
            revision.setDescripcion(revisionActualizada.getDescripcion());
            return revisionesRepository.save(revision);
        });
    }

    // Eliminar
    public boolean eliminarRevision(Long id) {
        if (revisionesRepository.existsById(id)) {
            revisionesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
