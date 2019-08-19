package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Country;

public interface ICountryService {
	public boolean insertar(Country country);

	public boolean modificar(Country country);

	public void eliminar(int idCountry);

	public Country listarId(int idCountry);

	List<Country> listar();

	List<Country> buscarNombre(String nameCountry);
}
