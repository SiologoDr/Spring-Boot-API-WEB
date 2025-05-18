package cl.api.buho.demo.repositories;

import cl.api.buho.demo.models.Revisiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRevisionesRepository extends JpaRepository<Revisiones, Long>{
}
