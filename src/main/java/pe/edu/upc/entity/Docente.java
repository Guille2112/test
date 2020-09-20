package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "Docente")
public class Docente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDocente;
	
	@Size (min=3 ,max=40)
	@NotEmpty(message="No puede estar vacío")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "nameDocente", length = 40, nullable = false)
	private String nameDocente;
	
	@Size (min=3 ,max=40)
	@NotEmpty(message="No puede estar vacío")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "apellidoDocente", length = 40, nullable = false)
	private String apellidoDocente;
	
	@Size (min=8 ,max=8)
	@NotEmpty(message="No puede estar vacío")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "dniDocente", length = 8, nullable = false,unique =true)
	private String dniDocente;
	
	

	public Docente() {
		super();
	}



	public Docente(int idDocente, String nameDocente, String apellidoDocente, String dniDocente) {
		super();
		this.idDocente = idDocente;
		this.nameDocente = nameDocente;
		this.apellidoDocente = apellidoDocente;
		this.dniDocente = dniDocente;
	}



	public int getIdDocente() {
		return idDocente;
	}



	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}



	public String getNameDocente() {
		return nameDocente;
	}



	public void setNameDocente(String nameDocente) {
		this.nameDocente = nameDocente;
	}



	public String getApellidoDocente() {
		return apellidoDocente;
	}



	public void setApellidoDocente(String apellidoDocente) {
		this.apellidoDocente = apellidoDocente;
	}



	public String getDniDocente() {
		return dniDocente;
	}



	public void setDniDocente(String dniDocente) {
		this.dniDocente = dniDocente;
	}

	
	

}

