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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Seccion")
public class Seccion implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSeccion;
	
	@Size (min=3 ,max=4)
	@NotEmpty(message="No puede estar vacío")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "nameSeccion", length = 60, nullable = false)
	private String nameSeccion;
	
	@ManyToOne
	@JoinColumn(name = "idDocente", nullable = false)
	private Docente docente;
	
	@Size (min=3 ,max=40)
	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "nameCurso", length = 40, nullable = false)
	private String nameCurso;

	public Seccion() {
		super();
	}

	public Seccion(int idSeccion, String nameSeccion, Docente docente, String nameCurso) {
		super();
		this.idSeccion = idSeccion;
		this.nameSeccion = nameSeccion;
		this.docente = docente;
		this.nameCurso = nameCurso;
	}

	public int getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}

	public String getNameSeccion() {
		return nameSeccion;
	}

	public void setNameSeccion(String nameSeccion) {
		this.nameSeccion = nameSeccion;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	
	public String getNameCurso() {
		return nameCurso;
	}

	public void setNameCurso(String nameCurso) {
		this.nameCurso = nameCurso;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

