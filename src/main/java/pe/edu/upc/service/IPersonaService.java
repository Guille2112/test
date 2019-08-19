package pe.edu.upc.service;

import java.util.Date;
import java.util.List;

import pe.edu.upc.entity.Persona;

public interface IPersonaService {
	
	public boolean insertar(Persona persona);
	public boolean modificar(Persona persona);
	public void eliminar(int idPersona);
	public Persona listarId(int idPersona);
	List<Persona> listar();
	List<Persona> findByDniPersona(String dni);
	List<Persona> buscarNombre(String namePersona);
	List<Persona> buscarEmail(String emailPersona);
	List<Persona> buscarCiudad(String nameCity);
	List<Persona> findByBirthDatePersona(Date birthDatePersona);
	public Persona buscarPorId(int id);

}
