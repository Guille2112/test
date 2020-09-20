package pe.edu.upc.service;

import java.util.List;
import pe.edu.upc.entity.Curso;

public interface ICursoService {
	public boolean insertar(Curso curso);

	public boolean modificar(Curso curso);

	public void eliminar(int idCurso);

	public Curso listarId(int idCurso);

	 List<Curso> listar();
	// public List<Matricula>buscarCursosyenos();
     List<Curso> findByCurso(String nameCurso);
 	List<Curso> buscarNombre(String nameCurso);
}
