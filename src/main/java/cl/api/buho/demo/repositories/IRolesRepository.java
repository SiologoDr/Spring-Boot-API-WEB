package cl.api.buho.demo.repositories;

import cl.api.buho.demo.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, Long>{
}