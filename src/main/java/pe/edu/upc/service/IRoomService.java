package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Room;



public interface IRoomService {

	public boolean insertar(Room room);

	public boolean modificar(Room room);

	public void eliminar(int idRoom);

	public Room listarId(int idRoom);

	List<Room> listar();

	List<Room> buscarNombre(String nameRoom);

}
