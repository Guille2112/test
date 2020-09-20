package pe.edu.upc.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.entity.Curso;


@Repository
public interface ICursoDAO extends JpaRepository <Curso ,Integer>{
	List<Curso> findBynameCursoContaining( String nameCurso);
	
	
	//@Query ("from Matricula m group by m.curso.idCurso having Count(m.curso.idCurso)<=10")
	// public List<Matricula>buscarCursosyenos();
	
	@Query("from Curso r where r.nameCurso = :nameCurso") 
	List<Curso> buscarNombre(@Param("nameCurso")String nameCurso);

}
