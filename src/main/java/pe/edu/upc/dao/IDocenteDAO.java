package pe.edu.upc.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.entity.Docente;

@Repository
public interface IDocenteDAO extends JpaRepository<Docente, Integer> {
	List<Docente> findByDniDocenteContaining( String dniDocente);
	
	@Query("from Docente r where r.dniDocente = :dniDocente") 
	List<Docente> buscarDni(@Param("dniDocente")String dniDocente);
}
