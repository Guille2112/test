package pe.edu.upc.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.entity.Alumno;

@Repository
public interface IAlumnoDAO extends JpaRepository<Alumno, Integer>{
	
	List<Alumno> findByDniAlumnoContaining( String dniAlumno);
List<Alumno>findBydniAlumno(String dniAlumno);


@Query("from Alumno r where r.dniAlumno = :dniAlumno") 
List<Alumno> buscarDni(@Param("dniAlumno")String dniAlumno);

}