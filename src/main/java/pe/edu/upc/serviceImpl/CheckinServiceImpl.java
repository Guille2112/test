package pe.edu.upc.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.ICheckinDAO;
import pe.edu.upc.entity.Checkin;
import pe.edu.upc.service.ICheckinService;

@Service
public class CheckinServiceImpl implements ICheckinService {

	@Autowired
	private ICheckinDAO dCheckin;

	@Override
	@Transactional
	public boolean insertar(Checkin chekin) {
		Checkin objCheckin = dCheckin.save(chekin);
		if (objCheckin == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Checkin chekin) {
		boolean flag = false;
		try {
			dCheckin.save(chekin);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	public void eliminar(int CidCheckin) {
		dCheckin.delete(CidCheckin);

	}

	@Override
	public Checkin listarId(int CidCheckin) {
		return dCheckin.findOne(CidCheckin);
	}

	@Override
	public List<Checkin> listar() {
		return dCheckin.findAll();
	}

	@Override
	public List<Checkin> findBynameCheckin(String nameCheckin) {
		return dCheckin.buscarNombre(nameCheckin);
	}

	@Override
	public List<Checkin> findByDateCheckin(Date dateCheckin) {
		return dCheckin.findByDateCheckin(dateCheckin);
	}

	@Override
	public List<Checkin> buscarxfechaReserva(Date birthDateReservation) {
		return dCheckin.buscarxfechaReserva(birthDateReservation);
	}

}
