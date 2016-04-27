package dao;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Propiedad {

	private int				id;
	private String			nombre;
	private Set<Actividad>	actividades;

	public Propiedad() {

	}

	public Propiedad(String nombre) {
		this.nombre = nombre;
	}

	public Propiedad(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
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

	public Set<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(Set<Actividad> actividades) {
		this.actividades = actividades;
	}

	@Override
	public String toString() {
		return "Propiedad [id=" + id + ", nombre=" + nombre + "]";
	}
}