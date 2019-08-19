package pe.edu.upc.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dao.IReservationDAO;
import pe.edu.upc.entity.DetallePedido;
import pe.edu.upc.entity.Reservation;
import pe.edu.upc.service.IReservationService;

@Service
public class ReservationServiceImpl implements IReservationService {
	@Autowired
	private IReservationDAO dReservation;
	@Override
	public boolean insertar(Reservation reservation) {
		Reservation objReservation= dReservation.save(reservation);
		if (objReservation == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean modificar(Reservation reservation) {
		boolean flag = false;
		try {
			dReservation.save(reservation);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	public void eliminar(int idReservation) {
		dReservation.delete(idReservation);
		
	}

	@Override
	public Reservation listarId(int idReservation) {
		return dReservation.findOne(idReservation);
	}

	@Override
	public List<Reservation> listar() {
		return dReservation.findAll();
	}

	@Override
	public List<Reservation> buscarReservationxPersona(String namePersona) {
		
		return dReservation.buscarReservationxPersona(namePersona);
	}

	@Override
	public List<Reservation> findByBirthDateReservation(Date birthDateReservation) {
		return dReservation.findByBirthDateReservation(birthDateReservation);
	}

	

	@Override
	public List<DetallePedido> buscarDetallePedido(String codigoReservacion) {
		return dReservation.buscarDetallePedido(codigoReservacion);
	}

	@Override
	public List<DetallePedido> buscarDetallePedidoxid(int idReservation) {
		return dReservation.buscarDetallePedidoxid(idReservation);
	}

}
