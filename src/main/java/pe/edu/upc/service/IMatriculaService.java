package pe.edu.upc.service;

import java.util.List;
//import pe.edu.upc.entity.Curso;
import pe.edu.upc.entity.Matricula;

public interface IMatriculaService {

	public boolean insertar(Matricula matricula);

	public boolean modificar(Matricula matricula);

	public void eliminar(int idMatricula);

	public Matricula listarId(int idMatricula);

	public List<Matricula> listar();
	//List<Matricula>findByAlumnoandSeccionandCurso(String nameAlumno ,String nameSeccion,String nameCurso);
	
	//public List<Curso> litarCursosLLenos();
	//public List<Matricula> litarMatriculasRepetidas();	

}
