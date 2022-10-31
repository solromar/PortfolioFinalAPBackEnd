
package com.portfoliosr.solromar.Repository;

import com.portfoliosr.solromar.Entity.Persona;/*lo trae de mi proyecto*/
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;/*para los metodos, los tiene y no necesito escribirlo, con ctrl sobre JPA*/
import org.springframework.stereotype.Repository;/*le digo que es un repositorio*/

@Repository
public interface IPersonaRepository extends JpaRepository<Persona,Integer>{
    public Optional <Persona> findByNombre (String nombre);
    public boolean existsByNombre (String nombre);  
}
