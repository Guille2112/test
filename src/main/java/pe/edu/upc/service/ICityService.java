package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.City;



public interface ICityService {

	public boolean insertar(City city);

	public boolean modificar(City city);

	public void eliminar(int idCity);

	public City listarId(int idCity);

	List<City> listar();

	List<City> buscarCity(String nameCity);
	
	List<City> buscarCountry(String nameCountry);

	
}
