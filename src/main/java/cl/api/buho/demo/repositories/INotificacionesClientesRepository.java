package cl.api.buho.demo.repositories;

import cl.api.buho.demo.models.NotificacionesClientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificacionesClientesRepository extends JpaRepository<NotificacionesClientes, Long>{
}
