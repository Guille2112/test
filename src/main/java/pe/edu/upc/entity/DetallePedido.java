package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DetallePedido")
public class DetallePedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetallePedido;

	@ManyToOne
	@JoinColumn(name="idRoom")
	private Room room;
	@ManyToOne
	@JoinColumn(name="idReservation")
	private Reservation reservation;

	@Column(name = "cantidad")
	private int cantidad;
	

	public DetallePedido() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DetallePedido(int idDetallePedido, Room room, Reservation reservation, int cantidad) {
		super();
		this.idDetallePedido = idDetallePedido;
		this.room = room;
		this.reservation = reservation;
		this.cantidad = cantidad;
	}


	public int getIdDetallePedido() {
		return idDetallePedido;
	}


	public void setIdDetallePedido(int idDetallePedido) {
		this.idDetallePedido = idDetallePedido;
	}


	public Room getRoom() {
		return room;
	}


	public void setRoom(Room room) {
		this.room = room;
	}


	public Reservation getReservation() {
		return reservation;
	}


	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


		
	
}

	