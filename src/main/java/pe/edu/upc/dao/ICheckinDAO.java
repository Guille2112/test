package pe.edu.upc.dao;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Checkin;


@Repository
public interface ICheckinDAO extends JpaRepository<Checkin,Integer>{
	
	@Query("from Checkin p where p.nameCheckin like %:nameCheckin%") 
	List<Checkin> buscarNombre(@Param("nameCheckin")String nameCheckin);
	@Query("from Checkin p where p.reservation.birthDateReservation like :birthDateReservation") 
	List<Checkin> buscarxfechaReserva(@Param("birthDateReservation")Date birthDateReservation);
	List<Checkin> findByDateCheckin(Date dateCheckin);
}
