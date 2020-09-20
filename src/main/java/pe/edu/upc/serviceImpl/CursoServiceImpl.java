package pe.edu.upc.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.dao.ICursoDAO;
import pe.edu.upc.entity.Curso;
import pe.edu.upc.service.ICursoService;

@Service
public class CursoServiceImpl implements ICursoService {

	@Autowired
	private ICursoDAO cDAO;

	@Override
	public void eliminar(int idCurso) {
		cDAO.delete(idCurso);		
	}

	@Override
	public Curso listarId(int idCurso) {
		return cDAO.findOne(idCurso);
	}

	@Override
	public List<Curso> listar() {
		return cDAO.findAll();
	}

	@Override
	public boolean insertar(Curso curso) {
		Curso objCurso = cDAO.save(curso);
		if (objCurso == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean modificar(Curso curso) {
		boolean flag = false;
		try {
			cDAO.save(curso);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	public List<Curso> findByCurso(String nameCurso) {
	
		return cDAO.findBynameCursoContaining(nameCurso);
	}

	@Override
	public List<Curso> buscarNombre(String nameCurso) {
		// TODO Auto-generated method stub
		return cDAO.buscarNombre(nameCurso);
	}

//	@Override
	//public List<Matricula> buscarCursosyenos() {
	
		//return cDAO.buscarCursosyenos();
	//}


}

