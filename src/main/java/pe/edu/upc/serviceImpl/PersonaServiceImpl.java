package pe.edu.upc.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dao.IPersonaDAO;
import pe.edu.upc.entity.Persona;
import pe.edu.upc.service.IPersonaService;

@Service //usa para marcar una clase como una clase de lógica de negocios.
public class PersonaServiceImpl implements IPersonaService{
	
	@Autowired //  maneja solo la parte del cableado. 
	private IPersonaDAO dPersona;

	@Override
	public boolean insertar(Persona persona) {
		Persona objPersona = dPersona.save(persona);
		if (objPersona == null){
			return false;
		}else{
			return true;
		}		
	}

	@Override
	public boolean modificar(Persona persona) {
		boolean flag = false;
		try {
			dPersona.save(persona);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return flag;
	}

	@Override
	public void eliminar(int idPersona) {
		
		dPersona.delete(idPersona);
		
	}

	@Override
	public Persona listarId(int idPersona) {
		
		return dPersona.findOne(idPersona);
		
	}

	@Override
	public List<Persona> listar() {
		
		return dPersona.findAll();
		
	}

	@Override
	public List<Persona> findByDniPersona(String dni) {
		
		return dPersona.findByDniPersona(dni);
		
	}

	@Override
	public List<Persona> buscarNombre(String namePersona) {
		
		return dPersona.buscarNombre(namePersona);
		
	}

	@Override
	public List<Persona> buscarEmail(String emailPersona) {
		
		return dPersona.buscarEmail(emailPersona);
		
	}

	@Override
	public List<Persona> findByBirthDatePersona(Date birthDatePersona) {
		
		return dPersona.findByBirthDatePersona(birthDatePersona);
		
	}
	@Override
	public Persona buscarPorId(int id) {
		// TODO Auto-generated method stub
		return dPersona.findOne(id);
	}

	@Override
	public List<Persona> buscarCiudad(String nameCity) {
		// TODO Auto-generated method stub
		return dPersona.buscarCiudad(nameCity);
	}
	

}
