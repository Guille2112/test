package pe.edu.upc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Room;




@Repository
public interface IRoomDAO  extends JpaRepository<Room, Integer>{

	@Query("from Room r where r.nameRoom like %:nameRoom%")
	List<Room> buscarNombre(@Param("nameRoom")String nameRoom);
	
}
