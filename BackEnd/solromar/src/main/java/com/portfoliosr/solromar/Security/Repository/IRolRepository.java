
package com.portfoliosr.solromar.Security.Repository;

import com.portfoliosr.solromar.Security.Entity.Rol;
import com.portfoliosr.solromar.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
