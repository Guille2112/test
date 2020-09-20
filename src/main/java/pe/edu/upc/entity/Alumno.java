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
@Table(name = "Alumno")
public class Alumno implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAlumno;
	
	@Size (min=3 ,max=40)
	@NotEmpty(message="No puede estar vacío")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "nameAlumno", length = 40, nullable = false)
	private String nameAlumno;
	
	@Size (min=3 ,max=40)
	@NotEmpty(message="No puede estar vacío")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "apellidoAlumno", length = 40, nullable = false)
	private String apellidoAlumno;
	
	
	@Size (min=8 ,max=8)
	@NotBlank(message="No puede estar en blanco")
	@NotEmpty(message="No puede estar vacío")

	@Column(name = "dniAlumno", length = 8, nullable = false,unique =true)
	private String dniAlumno;

	public Alumno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Alumno(int idAlumno, String nameAlumno, String apellidoAlumno, String dniAlumno) {
		super();
		this.idAlumno = idAlumno;
		this.nameAlumno = nameAlumno;
		this.apellidoAlumno = apellidoAlumno;
		this.dniAlumno = dniAlumno;
	}

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	public String getNameAlumno() {
		return nameAlumno;
	}

	public void setNameAlumno(String nameAlumno) {
		this.nameAlumno = nameAlumno;
	}

	public String getApellidoAlumno() {
		return apellidoAlumno;
	}

	public void setApellidoAlumno(String apellidoAlumno) {
		this.apellidoAlumno = apellidoAlumno;
	}

	public String getDniAlumno() {
		return dniAlumno;
	}

	public void setDniAlumno(String dniAlumno) {
		this.dniAlumno = dniAlumno;
	}
	


}
	



	








	