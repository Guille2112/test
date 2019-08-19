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
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "City")
public class City implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCity;

	@NotNull
	@Column(name = "nameCity", length = 60, nullable = false)
	private String nameCity;
	
	@ManyToOne 
	@JoinColumn(name = "idCountry", nullable = false)
	private Country country;
	
	
	public City(int idCity, String nameCity, Country country) {
		super();
		this.idCity = idCity;
		this.nameCity = nameCity;
		this.country = country;
	}


	public City() {
		super();
	}


	public int getIdCity() {
		return idCity;
	}


	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}


	public String getNameCity() {
		return nameCity;
	}


	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}

}
