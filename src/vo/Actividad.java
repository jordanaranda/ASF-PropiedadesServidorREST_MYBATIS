package vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Actividad {

	private int		idActividad;
	private String	nombre;
	private String descripcion;
	private int edad_minima;

	public Actividad() {

	}

	public Actividad(int idActividad, String nombre, String descripcion, int edad_minima) {
		super();
		this.idActividad = idActividad;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.edad_minima = edad_minima;
	}

	public int getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEdad_minima() {
		return edad_minima;
	}

	public void setEdad_minima(int edad_minima) {
		this.edad_minima = edad_minima;
	}
	
	public CustomActividad toCustomActividad() {
		CustomActividad ca = new CustomActividad();
		ca.setIdActividad(idActividad);
		ca.setNombre(nombre);
		return ca;
	}
}