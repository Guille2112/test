package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "Checkin")
public class Checkin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCheckin;
	@OneToOne
	@JoinColumn(name= "idReservation", nullable=false)
	private Reservation reservation;
	
	@Size(min = 8, max = 8)
	@Column(name = "nameCheckin", length = 8, nullable = false)
	private String nameCheckin;
	
	@NotNull
	@Future(message = "No puedes seleccionar un dia que ya paso")
	@Temporal(TemporalType.DATE)
	@Column(name = "dateCheckin")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCheckin;

	public Checkin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Checkin(int idCheckin, Reservation reservation, String nameCheckin, Date dateCheckin) {
		super();
		this.idCheckin = idCheckin;
		this.reservation = reservation;
		this.nameCheckin = nameCheckin;
		this.dateCheckin = dateCheckin;
	}

	public int getIdCheckin() {
		return idCheckin;
	}

	public void setIdCheckin(int idCheckin) {
		this.idCheckin = idCheckin;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public String getNameCheckin() {
		return nameCheckin;
	}

	public void setNameCheckin(String nameCheckin) {
		this.nameCheckin = nameCheckin;
	}

	public Date getDateCheckin() {
		return dateCheckin;
	}

	public void setDateCheckin(Date dateCheckin) {
		this.dateCheckin = dateCheckin;
	}

	

}
