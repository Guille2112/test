package pe.edu.upc.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.dao.IMatriculaDAO;
import pe.edu.upc.entity.Alumno;
import pe.edu.upc.entity.Matricula;
import pe.edu.upc.entity.Seccion;
import pe.edu.upc.service.IMatriculaService;

@Service
public class MatriculaServiceImpl implements IMatriculaService{

	@Autowired
	private IMatriculaDAO mDAO;

	@Override
	public boolean insertar(Matricula matricula) {
		

		Alumno objAlumno = matricula.getAlumno();
		Seccion objSeccion = matricula.getSeccion();
	int contador=0;
		List<Matricula> matriculas = mDAO.findAll();
		
		for (int i = 0; i<matriculas.size(); i++) {
			if(matriculas.get(i).getAlumno().getIdAlumno() == objAlumno.getIdAlumno()&& 
					matriculas.get(i).getSeccion().getIdSeccion() == objSeccion.getIdSeccion()
					) {
				return false;
			}
			
		}
		for (int o = 0; o<matriculas.size(); o++) {
			
	
			if( matriculas.get(o).getSeccion().getIdSeccion()==objSeccion.getIdSeccion()) {
				contador =contador+1;
				if (contador==10)
				{
					return false;
				}
					
			}
			
		}
		
		Matricula objMatricula= mDAO.save(matricula);
		if (objMatricula == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean modificar(Matricula matricula) {
		boolean flag = false;
		try {
			mDAO.delete(matricula.getIdMatricula());
			mDAO.save(matricula);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	public void eliminar(int idMatricula) {
		mDAO.delete(idMatricula);
		
	}

	@Override
	public Matricula listarId(int idMatricula) {
		// TODO Auto-generated method stub
		return mDAO.findOne(idMatricula);
	}

	@Override
	public List<Matricula> listar() {
		// TODO Auto-generated method stub
		return mDAO.findAll();
	}

	//@Override
//	public List<Matricula> findByAlumnoandSeccionandCurso(String nameAlumno, String nameSeccion, String nameCurso) {
		
	//	return mDAO.findByAlumnoAndSeccionAndCursoContaining(nameAlumno, nameSeccion, nameCurso);
	//}

	

	//@Override
	//public List<Curso> litarCursosLLenos() {

		//return mDAO.buscarCursosllenos();
	//}


	
	
	
}
