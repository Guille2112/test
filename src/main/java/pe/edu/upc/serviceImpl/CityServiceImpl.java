package pe.edu.upc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.ICityDAO;
import pe.edu.upc.entity.City;
import pe.edu.upc.service.ICityService;



@Service
public class CityServiceImpl implements ICityService{

	@Autowired
	private ICityDAO dCity;
	
	@Override
	@Transactional
	public boolean insertar(City city) {
		City objCity = dCity.save(city);
		if (objCity == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	@Transactional
	public boolean modificar(City city) {
		boolean flag = false;
		try {
			dCity.save(city);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	public void eliminar(int idCity) {
	
		dCity.delete(idCity);
	}

	@Override
	public City listarId(int idCity) {
		return dCity.findOne(idCity);
	}

	@Override
	public List<City> listar() {
		return dCity.findAll();
	}

	@Override
	public List<City> buscarCity(String nameCity) {
		return dCity.buscarCity(nameCity);
	}

	@Override
	public List<City> buscarCountry(String nameCountry) {
		return dCity.buscarCountry(nameCountry);
	}

	
}
