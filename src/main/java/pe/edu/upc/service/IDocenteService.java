package pe.edu.upc.service;

import java.util.List;
import pe.edu.upc.entity.Docente;

public interface IDocenteService {
	public boolean insertar(Docente docente);
	public boolean modificar(Docente docente);
	public void eliminar(int idDocente);
	public Docente listarId(int idDocente);

	List<Docente> listar();
	List<Docente> buscarDni(String dniDocente);
	List<Docente> findByDocente(String dniDocente);

}
