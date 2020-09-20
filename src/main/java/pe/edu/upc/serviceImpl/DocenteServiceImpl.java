package pe.edu.upc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.dao.IDocenteDAO;
import pe.edu.upc.entity.Docente;
import pe.edu.upc.service.IDocenteService;

@Service
public class DocenteServiceImpl implements IDocenteService {

	@Autowired
	private IDocenteDAO dDAO;

	@Override
	public void eliminar(int idDocente) {
		dDAO.delete(idDocente);
		
	}

	@Override
	public Docente listarId(int idDocente) {
		return dDAO.findOne(idDocente);
	}

	@Override
	public List<Docente> listar() {
		return dDAO.findAll();
	}

	@Override
	public boolean insertar(Docente docente) {
		Docente objDocente = dDAO.save(docente);
		if (objDocente == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean modificar(Docente docente) {
		boolean flag = false;
		try {
			
			dDAO.save(docente);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	public List<Docente> findByDocente(String dniDocente) {
		
		return dDAO.findByDniDocenteContaining(dniDocente);
	}

	@Override
	public List<Docente> buscarDni(String dniDocente) {
		// TODO Auto-generated method stub
		return dDAO.buscarDni(dniDocente);
	}

	
}

