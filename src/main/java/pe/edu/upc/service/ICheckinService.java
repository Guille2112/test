package pe.edu.upc.service;

import java.util.Date;
import java.util.List;

import pe.edu.upc.entity.Checkin;



public interface ICheckinService {

	public boolean insertar(Checkin chekin);
	public boolean modificar(Checkin chekin);
	public void eliminar(int CidCheckin);
	public Checkin listarId(int CidCheckin);
	List<Checkin> listar();

	List <Checkin>findBynameCheckin(String nameCheckin);
	List <Checkin>findByDateCheckin(Date dateCheckin);	
	List <Checkin>buscarxfechaReserva(Date birthDateReservation);	
}
