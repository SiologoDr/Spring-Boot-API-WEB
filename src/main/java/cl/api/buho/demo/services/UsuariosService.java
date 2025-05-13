package cl.api.buho.demo.services;

import cl.api.buho.demo.models.Usuarios;
import cl.api.buho.demo.repositories.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    private IUsuariosRepository usuariosRepository;

    // Crear
    public Usuarios crearUsuario(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    // Listar todos
    public List<Usuarios> listarUsuarios() {
        return usuariosRepository.findAll();
    }

    // Buscar por ID
    public Optional<Usuarios> obtenerUsuarioPorId(Long id) {
        return usuariosRepository.findById(id);
    }

    // Actualizar
    public Optional<Usuarios> actualizarUsuario(Long id, Usuarios usuarioActualizado) {
        return usuariosRepository.findById(id).map(usuario -> {
            usuario.setUsuario(usuarioActualizado.getUsuario());
            usuario.setContraseña(usuarioActualizado.getContraseña());
            usuario.setRol(usuarioActualizado.getRol());
            return usuariosRepository.save(usuario);
        });
    }

    // Eliminar
    public boolean eliminarUsuario(Long id) {
        if (usuariosRepository.existsById(id)) {
            usuariosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
