package pe.edu.upc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.City;



@Repository
public interface ICityDAO extends JpaRepository<City, Integer>{

	@Query("from City ci where ci.nameCity like %:nameCity%")
	List<City> buscarCity(@Param("nameCity") String nameCity);
	
	//Country
	@Query("from City ci where ci.country.nameCountry like %:nameCountry%")
	List<City> buscarCountry(@Param("nameCountry") String nameCountry);

}
