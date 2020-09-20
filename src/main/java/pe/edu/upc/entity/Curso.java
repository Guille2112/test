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
@Table(name = "Curso")
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCurso;
	
	@Size (min=3 ,max=40)
	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "nameCurso", length = 40, nullable = false)
	private String nameCurso;

	public Curso() {
		super();
	}

	public Curso(int idCurso, String nameCurso) {
		super();
		this.idCurso = idCurso;
		this.nameCurso = nameCurso;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
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
