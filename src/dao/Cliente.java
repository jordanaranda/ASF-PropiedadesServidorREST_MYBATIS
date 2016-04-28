package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@XmlRootElement
@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@Column(name = "dni")
	private int				dni;

	@Column(name = "nombre")
	private String			nombre;

	@Column(name = "apellido")
	private String			apellido;

	@Column(name = "email")
	private String			email;

	@Column(name = "direccion")
	private String			direccion;

	@Column(name = "cp")
	private int				codigoPostal;

	@Column(name = "telefono")
	private int				telefono;

	@OneToMany(targetEntity = Alquiler.class, cascade = CascadeType.ALL)
	@Column(name = "dniCliente")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Alquiler>	alquileres;

	public Cliente() {

	}

	public Cliente(String nombre, String apellido, String email, String direccion, int codigoPostal, int telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.alquileres = new ArrayList<Alquiler>();
	}

	public Cliente(int dni, String nombre, String apellido, String email, String direccion, int codigoPostal, int telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.alquileres = new ArrayList<Alquiler>();
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public List<Alquiler> getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(List<Alquiler> alquileres) {
		this.alquileres = alquileres;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", direccion=" + direccion
				+ ", codigoPostal=" + codigoPostal + ", telefono=" + telefono + ", alquileres=" + alquileres + "]";
	}
}