package pe.edu.upc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.IDetallePedidoDAO;
import pe.edu.upc.entity.DetallePedido;
import pe.edu.upc.service.IDetallePedidoService;

@Service
public class DetallePedidoServiceImpl implements IDetallePedidoService {

	@Autowired
	private IDetallePedidoDAO dDetallePedido;

	@Override
	@Transactional
	public boolean insertar(DetallePedido detalleDetallePedido) {
		DetallePedido objDetallePedido = dDetallePedido.save(detalleDetallePedido);
		if (objDetallePedido == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(DetallePedido detalleDetallePedido) {
		boolean flag = false;
		try {
			dDetallePedido.save(detalleDetallePedido);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idDetallePedido) {

		dDetallePedido.delete(idDetallePedido);

	}

	
	@Override
	@Transactional(readOnly=true)
	public DetallePedido listarId(int idDetallePedido) {
		return dDetallePedido.findOne(idDetallePedido);
	}


	@Override
	@Transactional(readOnly=true)
	public List<DetallePedido> listar() {
		return dDetallePedido.findAll();
	}
	
	

}
