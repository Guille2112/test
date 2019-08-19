package pe.edu.upc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dao.IRoomDAO;
import pe.edu.upc.entity.Room;
import pe.edu.upc.service.IRoomService;

@Service
public class RoomServiceImpl implements IRoomService {

	@Autowired
	private IRoomDAO dRoom;
	
	@Override
	public boolean insertar(Room room) {
		Room objRoom = dRoom.save(room);
		if (objRoom == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean modificar(Room room) {
		boolean flag = false;
		try {
			dRoom.save(room);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	public void eliminar(int idRoom) {
		// TODO Auto-generated method stub
		dRoom.delete(idRoom);
	}

	@Override
	public Room listarId(int idRoom) {
		return dRoom.findOne(idRoom);
	}

	@Override
	public List<Room> listar() {
		return dRoom.findAll();
	}

	@Override
	public List<Room> buscarNombre(String nameRoom) {
		return dRoom.buscarNombre(nameRoom);
	}

}
