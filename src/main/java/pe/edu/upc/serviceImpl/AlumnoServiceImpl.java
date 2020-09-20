package pe.edu.upc.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.dao.IAlumnoDAO;
import pe.edu.upc.entity.Alumno;
import pe.edu.upc.service.IAlumnoService;

@Service
public class AlumnoServiceImpl implements IAlumnoService{

	@Autowired
	private IAlumnoDAO aDAO;

	@Override
	public void eliminar(int idAlumno) {
		aDAO.delete(idAlumno);	
	}

	@Override
	public Alumno listarId(int idAlumno) {
		return aDAO.findOne(idAlumno);
	}

	@Override
	public List<Alumno> listar() {
		return aDAO.findAll();
	}

	@Override
	public boolean insertar(Alumno alumno) {
		Alumno objAlumno = aDAO.save(alumno);
		if (objAlumno == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean modificar(Alumno alumno) {
		boolean flag = false;
		
		try {
           
			aDAO.save(alumno);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	public List<Alumno> findByAlumno(String dniAlumno) {
		return aDAO.findByDniAlumnoContaining(dniAlumno);
	}

	@Override
	public List<Alumno> findBydniAlumno(String dniAlumno) {
		// TODO Auto-generated method stub
		return aDAO.findBydniAlumno(dniAlumno);
	}

	@Override
	public List<Alumno> buscarDni(String dniAlumno) {
		// TODO Auto-generated method stub
		return aDAO.buscarDni(dniAlumno);
	}



	

	

	
	
}
