package pe.edu.upc.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.ICheckoutDAO;
import pe.edu.upc.entity.Checkout;
import pe.edu.upc.service.ICheckoutService;

@Service
public class CheckoutServiceImpl implements ICheckoutService{
	@Autowired
	private ICheckoutDAO dCheckout;

	@Override
	@Transactional
	public boolean insertar(Checkout chekout) {
		Checkout objCheckout = dCheckout.save(chekout);
		if (objCheckout == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Checkout chekout) {
		boolean flag = false;
		try {
			dCheckout.save(chekout);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	public void eliminar(int CidCheckout) {
		dCheckout.delete(CidCheckout);

	}

	@Override
	public Checkout listarId(int CidCheckout) {
		return dCheckout.findOne(CidCheckout);
	}

	@Override
	public List<Checkout> listar() {
		return dCheckout.findAll();
	}

	

	@Override
	public List<Checkout> findBynameCheckout(String nameCheckout) {
		return dCheckout.buscarNombre(nameCheckout);
	}

	@Override
	public List<Checkout> findByDateCheckout(Date dateCheckout) {
		return dCheckout.findByDateCheckout(dateCheckout);
	}
}
