package pe.edu.upc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.ICountryDAO;
import pe.edu.upc.entity.Country;
import pe.edu.upc.service.ICountryService;

@Service
public class CountryServiceImpl implements ICountryService {

	@Autowired
	private ICountryDAO dCountry;
	
	@Override
	@Transactional
	public boolean insertar(Country country) {
		Country objCountry = dCountry.save(country);
		if (objCountry == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Country country) {
		boolean flag = false;
		try {
			dCountry.save(country);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idCountry) {
		dCountry.delete(idCountry);
		
	}

	@Override
	@Transactional
	public Country listarId(int idCountry) {
		return dCountry.findOne(idCountry);
	}

	@Override
	public List<Country> listar() {
		return dCountry.findAll();
	}

	@Override
	public List<Country> buscarNombre(String nameCountry) {
		return dCountry.buscarNombre(nameCountry);
	}

}
