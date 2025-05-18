package cl.api.buho.demo.repositories;

import cl.api.buho.demo.models.Estados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadosRepositoy extends JpaRepository<Estados, Long>{
}
