package pe.edu.upc.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.dao.ISeccionDAO;
import pe.edu.upc.entity.Seccion;
import pe.edu.upc.service.ISeccionService;

@Service
public class SeccionServiceImpl implements ISeccionService{

	@Autowired
	private ISeccionDAO sDAO;

	@Override
	public void eliminar(int idSeccion) {
		sDAO.delete(idSeccion);		
	}

	@Override
	public Seccion listarId(int idSeccion) {
		return sDAO.findOne(idSeccion);
	}

	@Override
	public List<Seccion> listar() {
		return sDAO.findAll();
	}

	@Override
	public boolean insertar(Seccion seccion) {
		Seccion objSeccion = sDAO.save(seccion);
		if (objSeccion == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean modificar(Seccion seccion) {
		boolean flag = false;
		try {
			
			sDAO.save(seccion);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	public List<Seccion> findBySeccion(String nameSeccion) {
		
		return sDAO.findBynameSeccionContaining(nameSeccion);
	}

	@Override
	public List<Seccion> buscarNombre(String nameSeccion) {
		// TODO Auto-generated method stub
		return sDAO.buscarNombre(nameSeccion);
	}

	
}

