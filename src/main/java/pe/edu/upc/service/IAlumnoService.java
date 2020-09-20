package pe.edu.upc.service;

import java.util.List;
import pe.edu.upc.entity.Alumno;

public interface IAlumnoService {
	public boolean  insertar(Alumno alumno);

	public boolean  modificar(Alumno alumno);

	public void eliminar(int idAlumno);

	public Alumno listarId(int idAlumno);
    List<Alumno> buscarDni(String dniAlumno);
	List<Alumno> listar();
	List<Alumno> findByAlumno(String dniAlumno);
	List<Alumno> findBydniAlumno(String dniAlumno);
}
