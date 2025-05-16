package cl.api.buho.demo.repositories;

import cl.api.buho.demo.models.Tecnicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITecnicosRepository extends JpaRepository<Tecnicos, Long>{
}
