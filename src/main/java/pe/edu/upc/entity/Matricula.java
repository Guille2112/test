package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "Matricula")
public class Matricula implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMatricula;
	
	@ManyToOne
	@JoinColumn(name = "idAlumno", nullable = false)

	private Alumno alumno;
	
	@ManyToOne
	@JoinColumn(name = "idSeccion", nullable = false)
	private Seccion seccion;
	
	//@ManyToOne
	//@JoinColumn(name = "idCurso", nullable = false)
	//private Curso curso;
	

	public Matricula() {
		super();
	}

	

	public Matricula(int idMatricula, Alumno alumno, Seccion seccion, Curso curso) {
		super();
		this.idMatricula = idMatricula;
		this.alumno = alumno;
		this.seccion = seccion;
		//this.curso = curso;
	}



	public int getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	//public Curso getCurso() {
		//return curso;
	//}

	//public void setCurso(Curso curso) {
		//this.curso = curso;
	//}



	
}
