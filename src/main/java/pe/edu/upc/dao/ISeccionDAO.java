package pe.edu.upc.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.entity.Seccion;

@Repository
public interface ISeccionDAO extends JpaRepository<Seccion, Integer> {

	List<Seccion> findBynameSeccionContaining( String nameSeccion);
	@Query("from Seccion r where r.nameSeccion = :nameSeccion") 
	List<Seccion> buscarNombre(@Param("nameSeccion")String nameSeccion);
}
