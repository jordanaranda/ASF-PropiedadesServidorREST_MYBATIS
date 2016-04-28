package dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "actividad")
public class Actividad {

	@Id
	@Column(name = "idActividad")
	private int		idActividad;

	@Column(name = "nombre")
	private String	nombre;

	public Actividad() {

	}

	public Actividad(String nombre) {
		this.nombre = nombre;
	}

	public Actividad(int id, String nombre) {
		this.idActividad = id;
		this.nombre = nombre;
	}

	public int getId() {
		return idActividad;
	}

	public void setId(int id) {
		this.idActividad = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Actividad [id=" + idActividad + ", nombre=" + nombre + "]";
	}
}