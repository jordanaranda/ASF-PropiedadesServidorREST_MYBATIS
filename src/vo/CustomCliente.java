package vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomCliente {

	private int						dni;
	private String					nombre;
	private String					apellido;
	private String					email;
	private String					direccion;
	private int						codigoPostal;
	private int						telefono;
	private List<CustomAlquiler>	alquileres;

	public CustomCliente() {
		alquileres = new ArrayList<CustomAlquiler>();
	}

	public CustomCliente(int dni, String nombre, String apellido, String email, String direccion, int codigoPostal, int telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.alquileres = new ArrayList<CustomAlquiler>();
	}

	public CustomCliente(String nombre, String apellido, String email, String direccion, int codigoPostal, int telefono,
			List<CustomAlquiler> alquileres) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.alquileres = alquileres;
	}

	public CustomCliente(int dni, String nombre, String apellido, String email, String direccion, int codigoPostal, int telefono,
			List<CustomAlquiler> alquileres) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.alquileres = alquileres;
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

	public List<CustomAlquiler> getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(List<CustomAlquiler> alquileres) {
		this.alquileres = alquileres;
	}

	public Cliente toCliente() {
		Cliente cliente = new Cliente();
		cliente.setDni(dni);
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setEmail(email);
		cliente.setDireccion(direccion);
		cliente.setcp(codigoPostal);
		cliente.setTelefono(telefono);
		return cliente;
	}

	@Override
	public String toString() {
		return "CustomCliente [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", direccion=" + direccion
				+ ", codigoPostal=" + codigoPostal + ", telefono=" + telefono + "]";
	}
}