
package pe.edu.upc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dao.IUsuarioDao;
import pe.edu.upc.entity.Usuario;
import pe.edu.upc.service.IUsuarioService;

@Service
public class IUsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioDao uDao;

	@Override
	public boolean insertar(Usuario usuario) {
		Usuario objUsuario=uDao.save(usuario);
		if(objUsuario==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean modificar(Usuario usuario) {
		boolean flag=false;
		try {
			uDao.save(usuario);
			flag=true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		uDao.delete(id);
	}

	@Override
	public Usuario listarId(int id) {
		// TODO Auto-generated method stub
		return uDao.findOne(id);
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return uDao.findAll();
	}
	
	
	
}
