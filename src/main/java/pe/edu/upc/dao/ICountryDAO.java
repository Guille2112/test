package pe.edu.upc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Country;

@Repository
public interface ICountryDAO extends JpaRepository<Country, Integer> {
	@Query("from Country co where co.nameCountry like %:nameCountry%")
	List<Country> buscarNombre(@Param("nameCountry")String nameCountry);


	
}
