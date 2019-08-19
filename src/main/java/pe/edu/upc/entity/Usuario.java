package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;

	@Column(name = "clave", length = 50, nullable = false)
	private String clave;

	@Column(name = "tipo", length = 20, nullable = false)
	private String tipo;

	@Column(name = "estado", length = 1, nullable = false)
	private String estado;

	
	
	public Usuario(int id, String nombre, String clave, String tipo, String estado) {
		this.id = id;
		this.nombre = nombre;
		this.clave = clave;
		this.tipo = tipo;
		this.estado = estado;
	}
	
	public Usuario() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}