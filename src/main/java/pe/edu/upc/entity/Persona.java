package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity //Annotate all your entity beans 
@Table(name = "Persona") // define el nombre de la tabla en la base de datos
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPersona;

	@NotEmpty(message = "No puede estar vacío") //El objeto CharSequence, Collection, Map o Array no puede ser nulo ni vacío (tamaño> 0).
	@NotBlank(message = "No puede estar en blanco") //La cadena no es nula y la longitud es mayor que cero.
	@Column(name = "namePersona", length = 60, nullable = false)
	private String namePersona;

	@Size(min = 8, max = 8)
	@Column(name = "dniPersona", length = 8, nullable = false)
	private String dniPersona;

	@NotNull // El objeto CharSequence, Collection, Map o Array no puede ser nulo, sin embargo, puede estar vacío.
	@Past(message = "No puedes seleccionar un dia que todavia no existe") // validar que sea hoy o antes de hoy
	@Temporal(TemporalType.DATE) // afina Los datos temporales pueden tener una FECHA, HORA o TIMESTAMP de precisión
	@Column(name = "birthDatePersona")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDatePersona;

	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Email // PERMITE DIGITAR EL CORREO COMPLETO
	@Column(name = "emailPersona", length = 60, nullable = false)
	private String emailPersona;
	@Transient //se usa para indicar que un campo no debe persistir en la base de datos, es decir, su semántica es diferente
	private String fechaString;
	@ManyToOne
	@JoinColumn(name= "idCity", nullable=false)
	private City city;
	private String foto;
	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Persona(int idPersona, String namePersona, String dniPersona, Date birthDatePersona, String emailPersona,
			String fechaString, City city, String foto) {
		super();
		this.idPersona = idPersona;
		this.namePersona = namePersona;
		this.dniPersona = dniPersona;
		this.birthDatePersona = birthDatePersona;
		this.emailPersona = emailPersona;
		this.fechaString = fechaString;
		this.city = city;
		this.foto = foto;
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNamePersona() {
		return namePersona;
	}
	public void setNamePersona(String namePersona) {
		this.namePersona = namePersona;
	}
	public String getDniPersona() {
		return dniPersona;
	}
	public void setDniPersona(String dniPersona) {
		this.dniPersona = dniPersona;
	}
	public Date getBirthDatePersona() {
		return birthDatePersona;
	}
	public void setBirthDatePersona(Date birthDatePersona) {
		this.birthDatePersona = birthDatePersona;
	}
	public String getEmailPersona() {
		return emailPersona;
	}
	public void setEmailPersona(String emailPersona) {
		this.emailPersona = emailPersona;
	}
	public String getFechaString() {
		return fechaString;
	}
	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	

	
}
