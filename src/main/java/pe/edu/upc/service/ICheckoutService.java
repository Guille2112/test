package pe.edu.upc.service;

import java.util.Date;
import java.util.List;


import pe.edu.upc.entity.Checkout;

public interface ICheckoutService {
	public boolean insertar(Checkout chekout);
	public boolean modificar(Checkout chekout);
	public void eliminar(int CidCheckout);
	public Checkout listarId(int CidCheckout);
	List<Checkout> listar();

	List <Checkout>findBynameCheckout(String nameCheckout);
	List <Checkout>findByDateCheckout(Date dateCheckout);	
	
	
}
