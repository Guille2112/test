package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "Room")
public class Room  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRoom;
	
	@Column(name = "nameRoom", length = 60, nullable = false)
	private String nameRoom;
	
	@Column(name = "numBeds")
	private int numBeds;
	@Column(name = "numGauging")
	private int numGauging;
	@Column(name = "numPrice")
	private int numPrice;


	public Room() {
		super();
	}


	public Room(int idRoom, String nameRoom, int numBeds, int numGauging, int numPrice) {
		super();
		this.idRoom = idRoom;
		this.nameRoom = nameRoom;
		this.numBeds = numBeds;
		this.numGauging = numGauging;
		this.numPrice = numPrice;
	}


	public int getIdRoom() {
		return idRoom;
	}


	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}


	public String getNameRoom() {
		return nameRoom;
	}


	public void setNameRoom(String nameRoom) {
		this.nameRoom = nameRoom;
	}


	public int getNumBeds() {
		return numBeds;
	}


	public void setNumBeds(int numBeds) {
		this.numBeds = numBeds;
	}


	public int getNumGauging() {
		return numGauging;
	}


	public void setNumGauging(int numGauging) {
		this.numGauging = numGauging;
	}


	public int getNumPrice() {
		return numPrice;
	}


	public void setNumPrice(int numPrice) {
		this.numPrice = numPrice;
	}
	
	

}
