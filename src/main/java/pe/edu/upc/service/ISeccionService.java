package pe.edu.upc.service;

import java.util.List;


import pe.edu.upc.entity.Seccion;

public interface ISeccionService {
	public boolean insertar(Seccion seccion);

	public boolean modificar(Seccion seccion);

	public void eliminar(int idSeccion);

	public Seccion listarId(int idSeccion);

	List<Seccion> listar();
	List<Seccion> findBySeccion(String nameSeccion);
	List<Seccion> buscarNombre(String nameSeccion);
}
