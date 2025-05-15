package cl.api.buho.demo.repositories;

import cl.api.buho.demo.models.Personas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonasRepository extends JpaRepository<Personas, Long>{
}
