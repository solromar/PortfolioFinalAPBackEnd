 
package com.portfoliosr.solromar.Controller;

import com.portfoliosr.solromar.Dto.dtoExperiencia;
import com.portfoliosr.solromar.Entity.Experiencia;
import com.portfoliosr.solromar.Security.Controller.Mensaje;
import com.portfoliosr.solromar.Service.SExperiencia;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/explab")
@CrossOrigin(origins="https://frontendapsr.web.app")
public class CExperiencia {
    @Autowired
    SExperiencia sExperiencia;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = sExperiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!sExperiencia.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> create (@RequestBody dtoExperiencia dtoexp){
        if(StringUtils.isBlank(dtoexp.getNombreE())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(sExperiencia.existsBynombreE(dtoexp.getNombreE())){
            return new ResponseEntity(new Mensaje("La experiencia ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Experiencia experiencia= new Experiencia(dtoexp.getNombreE(), dtoexp.getDescripcionE(), dtoexp.getPeriodoE());
        sExperiencia.save(experiencia);
        return new ResponseEntity (new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/(id)")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperiencia dtoexp){
      //Validar si existe el ID
      if(!sExperiencia.existsById(id)){
          return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
      }
      //Compara nombre de experiencias
      if(sExperiencia.existsBynombreE(dtoexp.getNombreE()) && sExperiencia.getByNombreE(dtoexp.getNombreE()).get().getId() !=id){
          return new ResponseEntity(new Mensaje("La experiencia ya existe"), HttpStatus.BAD_REQUEST);
      }
      //No puede estar vacio el campo
      if (StringUtils.isBlank(dtoexp.getNombreE())){
          return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
      }
      
      Experiencia experiencia = sExperiencia.getOne(id).get();
      experiencia.setNombreE(dtoexp.getNombreE());
      experiencia.setDescripcionE((dtoexp.getDescripcionE()));
      experiencia.setPeriodoE((dtoexp.getPeriodoE()));
      
      sExperiencia.save(experiencia);
      return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
      
    } 
public ResponseEntity<?> delete(@PathVariable("id")int id){
    //valido si existe el id
    if(!sExperiencia.existsById(id)){
        return new ResponseEntity(new Mensaje("El id ya existe"), HttpStatus.BAD_REQUEST);
    }
    
    sExperiencia.delete(id);
    return new ResponseEntity (new Mensaje("Experiencia eliminada"), HttpStatus.OK);
}    
}
