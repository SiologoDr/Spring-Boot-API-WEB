package cl.api.buho.demo.repositories;

import cl.api.buho.demo.models.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientesRepository extends JpaRepository<Clientes, Long>{
}
