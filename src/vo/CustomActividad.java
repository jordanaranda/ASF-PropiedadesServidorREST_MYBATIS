package vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomActividad {

	private int		idActividad;
	private String	nombre;

	public CustomActividad() {

	}

	public CustomActividad(String nombre) {
		this.nombre = nombre;
	}

	public CustomActividad(int idActividad, String nombre) {
		this.idActividad = idActividad;
		this.nombre = nombre;
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

	@Override
	public String toString() {
		return "CustomActividad [idActividad=" + idActividad + ", nombre=" + nombre + "]";
	}
}