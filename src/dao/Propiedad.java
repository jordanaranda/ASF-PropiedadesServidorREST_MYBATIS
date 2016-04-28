package dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@XmlRootElement
@Entity
@Table(name = "propiedad")
public class Propiedad {

	@Id
	@Column(name = "idPropiedad")
	private int				id;

	@Column(name = "nombre")
	private String			nombre;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "propiedad_actividad", joinColumns = { @JoinColumn(name = "idPropiedad") }, inverseJoinColumns = {
			@JoinColumn(name = "idActividad") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Actividad>	actividades;

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

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	@Override
	public String toString() {
		return "Propiedad [id=" + id + ", nombre=" + nombre + "]";
	}
}