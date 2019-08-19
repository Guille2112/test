package pe.edu.upc.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Persona;

@Repository // anotación que marca la clase específica como un objeto de acceso a datos, lo que aclara su rol
public interface IPersonaDAO extends JpaRepository<Persona, Integer>{
	
	List<Persona> findByDniPersona(String dni);
	
	@Query("from Persona p where p.namePersona like %:namePersona%") //formas de definir una consulta que podemos ejecutar
	List<Persona> buscarNombre(@Param("namePersona")String namePersona);
	
	@Query("from Persona p where p.emailPersona like %:emailPersona%") //formas de definir una consulta que podemos ejecutar
	List<Persona> buscarEmail(@Param("emailPersona")String emailPersona);
	@Query("from Persona p where p.city.nameCity like %:nameCity%") //formas de definir una consulta que podemos ejecutar
	List<Persona> buscarCiudad(@Param("nameCity")String nameCity);
	
	List<Persona> findByBirthDatePersona(Date birthDatePersona);

}
