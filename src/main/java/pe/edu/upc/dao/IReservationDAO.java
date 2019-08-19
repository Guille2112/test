package pe.edu.upc.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.DetallePedido;
import pe.edu.upc.entity.Reservation;

@Repository
public interface IReservationDAO  extends JpaRepository<Reservation,Integer> {
	
	
	@Query("from Reservation r where r.persona.namePersona like %:namePersona%") 
	List<Reservation> buscarReservationxPersona(@Param("namePersona")String namePersona);
	@Query("from DetallePedido p where p.reservation.codigoReservacion = :codigoReservacion")
	public List<DetallePedido> buscarDetallePedido(@Param("codigoReservacion") String codigoReservacion);
	@Query("from DetallePedido p where p.reservation.idReservation = :idReservation")
	public List<DetallePedido> buscarDetallePedidoxid(@Param("idReservation") Integer idReservation);	
	List<Reservation> findByBirthDateReservation(Date birthDateReservation);
	
	
	
}
