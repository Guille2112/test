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
@Table(name = "Checkout")
public class Checkout implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCheckout;
	@OneToOne
	@JoinColumn(name= "idCheckin", nullable=false)
	private Checkin checkin;
	@Size(min = 8, max = 8)
	@Column(name = "nameCheckout", length = 8, nullable = false)
	private String nameCheckout;
	@Column(name = "Qdays")
	private int Qdays;
	
	@NotNull
	@Future(message = "No puedes seleccionar un dia que ya paso ")
	@Temporal(TemporalType.DATE)
	@Column(name = "dateCheckout")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCheckout;
	@OneToOne
	@JoinColumn(name= "idReservation", nullable=false)
	private Reservation reservation;
	public Checkout() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Checkout(int idCheckout, Checkin checkin, String nameCheckout, int qdays, Date dateCheckout,
			Reservation reservation) {
		super();
		this.idCheckout = idCheckout;
		this.checkin = checkin;
		this.nameCheckout = nameCheckout;
		Qdays = qdays;
		this.dateCheckout = dateCheckout;
		this.reservation = reservation;
	}
	public int getIdCheckout() {
		return idCheckout;
	}
	public void setIdCheckout(int idCheckout) {
		this.idCheckout = idCheckout;
	}
	public Checkin getCheckin() {
		return checkin;
	}
	public void setCheckin(Checkin checkin) {
		this.checkin = checkin;
	}
	public String getNameCheckout() {
		return nameCheckout;
	}
	public void setNameCheckout(String nameCheckout) {
		this.nameCheckout = nameCheckout;
	}
	public int getQdays() {
		return Qdays;
	}
	public void setQdays(int qdays) {
		Qdays = qdays;
	}
	public Date getDateCheckout() {
		return dateCheckout;
	}
	public void setDateCheckout(Date dateCheckout) {
		this.dateCheckout = dateCheckout;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	


}
