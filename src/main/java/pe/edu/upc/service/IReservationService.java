package pe.edu.upc.service;

import java.util.Date;
import java.util.List;

import pe.edu.upc.entity.DetallePedido;
import pe.edu.upc.entity.Reservation;

public interface IReservationService {
	public boolean insertar(Reservation reservation);
	public boolean modificar(Reservation reservation);
	public void eliminar(int idReservation);
	public Reservation listarId(int idReservation);
	List<Reservation> listar();
	List<Reservation> buscarReservationxPersona(String namePersona);
	List<Reservation> findByBirthDateReservation(Date birthDateReservation);
	public List<DetallePedido> buscarDetallePedido(String codigoReservacion);
	public List<DetallePedido> buscarDetallePedidoxid(int idReservation);
}
