package cl.api.buho.demo.services;

import cl.api.buho.demo.models.Roles;
import cl.api.buho.demo.repositories.IRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService {

    @Autowired
    private IRolesRepository rolesRepository;

    // Crear
    public Roles crearRol(Roles rol) {
        return rolesRepository.save(rol);
    }

    // Listar todos
    public List<Roles> listarRoles() {
        return rolesRepository.findAll();
    }

    // Buscar por ID
    public Optional<Roles> obtenerRolPorId(Long id) {
        return rolesRepository.findById(id);
    }

    // Actualizar
    public Optional<Roles> actualizarRol(Long id, Roles rolActualizado) {
        return rolesRepository.findById(id).map(rol -> {
            rol.setRol(rolActualizado.getRol());
            rol.setDescripcion(rolActualizado.getDescripcion());
            return rolesRepository.save(rol);
        });
    }

    // Eliminar
    public boolean eliminarRol(Long id) {
        if (rolesRepository.existsById(id)) {
            rolesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
