package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Country")
public class Country implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCountry;
	
	@Column(name = "nameCountry", length = 60, nullable = false)
	private String nameCountry;
	
	
	public Country() {
	super();
	}
	
	public Country(int idCountry, String nameCountry) {
		super();
		this.idCountry = idCountry;
		this.nameCountry = nameCountry;
	}





	public int getIdCountry() {
		return idCountry;
	}


	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}


	public String getNameCountry() {
		return nameCountry;
	}


	public void setNameCountry(String nameCountry) {
		this.nameCountry = nameCountry;
	}

}
