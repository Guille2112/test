package pe.edu.upc.dao;
	
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.DetallePedido;

@Repository
public interface IDetallePedidoDAO extends JpaRepository<DetallePedido, Integer>{
	
}
