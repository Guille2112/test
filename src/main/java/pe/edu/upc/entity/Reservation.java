package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "Reservation")
public class Reservation implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReservation;
	
	@NotNull
	@Future(message = "No puedes seleccionar un dia que ya pasó")
	@Temporal(TemporalType.DATE)
	@Column(name = "birthDateReservation")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDateReservation;
	
	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "codigoReservacion", length = 60, nullable = false)
	private String codigoReservacion;
	
	public Reservation(int idReservation, Date birthDateReservation, String codigoReservacion, Room room,
			Persona persona, String fechaString) {
		super();
		this.idReservation = idReservation;
		this.birthDateReservation = birthDateReservation;
		this.codigoReservacion = codigoReservacion;
		this.room = room;
		this.persona = persona;
	}
	@ManyToOne
	@JoinColumn(name = "idRoom", nullable = false)
	private Room room;
	@ManyToOne
	@JoinColumn(name = "idPersona", nullable = false)
	private Persona persona;
	//FALTA USER

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(int idReservation, Date birthDateReservation, String codigoReservacion, Room room,
			Persona persona) {
		super();
		this.idReservation = idReservation;
		this.birthDateReservation = birthDateReservation;
		this.codigoReservacion = codigoReservacion;
		this.room = room;
		this.persona = persona;
	}

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public Date getBirthDateReservation() {
		return birthDateReservation;
	}

	public void setBirthDateReservation(Date birthDateReservation) {
		this.birthDateReservation = birthDateReservation;
	}

	public String getCodigoReservacion() {
		return codigoReservacion;
	}

	public void setCodigoReservacion(String codigoReservacion) {
		this.codigoReservacion = codigoReservacion;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	
	

}
