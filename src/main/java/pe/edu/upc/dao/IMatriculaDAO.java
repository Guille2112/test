package pe.edu.upc.dao;

//import java.util.List;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import pe.edu.upc.entity.Curso;
import pe.edu.upc.entity.Matricula;
@Repository
public interface IMatriculaDAO extends JpaRepository<Matricula, Integer> {
	//@Query(" select m from matricula m join fetch  m.idAlumno a join fetch m.idCurso c  where m.alumno.dniAlumno = :dniAlumno && m.curso.nameCurso = :nameCurso")
	//Optional<Matricula> fetchBycodigo(String dniAlumno);
	
	
	//@Query(" select m from matricula m join fetch  m.idAlumno a join fetch m.idCurso c  where m.alumno.dniAlumno = :dniAlumno && m.curso.nameCurso = :nameCurso")
		//Optional<Matricula> fetchBycodigo(String dniAlumno);
	
	//LOS QUERYS PARA VALIDAR
	//@Query (" select m.curso.idCurso from Matricula m group by m.curso.idCurso having Count(m.curso.idCurso)<=10")
	 //public List<Matricula>buscarCursosllenos();
	//@Query ("select m from Matricula m group by m.alumno.idAlumno, m.curso.idCurso having Count(m.curso.idCurso)>1")
	  //public List<Matricula>buscarMatriculasRepetidas();	
	
	//@Query ("from Curso m group by m.idCurso having Count(m.idCurso)<=10")
		// public List<Curso>buscarCursosllenos();
	
	
	//List<Matricula>findByAlumnoAndSeccionAndCursoContaining(String nameAlumno,String nameSeccion,String nameCurso);
	
	
	
	
}
