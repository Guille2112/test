
package pe.edu.upc.service;

import java.util.List;


import pe.edu.upc.entity.Usuario;

public interface IUsuarioService {
	public boolean insertar(Usuario usuario);

	public boolean modificar(Usuario usuario);

	public void eliminar(int id);

	public Usuario listarId(int id);

	List<Usuario> listar();
}
