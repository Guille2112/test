package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.DetallePedido;

public interface IDetallePedidoService {

	public boolean insertar(DetallePedido detallePedido);

	public boolean modificar(DetallePedido detallePedido);

	public void eliminar(int idDetallePedido);

	public DetallePedido listarId(int idDetallePedido);

	List<DetallePedido> listar();

}
