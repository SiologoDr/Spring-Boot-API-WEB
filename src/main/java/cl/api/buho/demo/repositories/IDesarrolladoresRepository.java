package cl.api.buho.demo.repositories;

import cl.api.buho.demo.models.Desarrolladores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDesarrolladoresRepository extends JpaRepository<Desarrolladores, Long>{
}
