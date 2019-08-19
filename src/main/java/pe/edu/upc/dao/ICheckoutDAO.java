package pe.edu.upc.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Checkout;


@Repository
public interface ICheckoutDAO extends JpaRepository<Checkout,Integer>{

	
	@Query("from Checkout p where p.nameCheckout like %:nameCheckout%") //formas de definir una consulta que podemos ejecutar
	List<Checkout> buscarNombre(@Param("nameCheckout")String nameCheckout);
	List<Checkout> findByDateCheckout(Date dateCheckout);
}
