package vo;

import java.util.ArrayList;
import java.util.List;

public class CustomPropiedad {

	private int idPropiedad;
	private String nombre;
	private List<CustomActividad> actividades;
	
	public CustomPropiedad() {
		actividades = new ArrayList<CustomActividad>();
	}

	public CustomPropiedad(String nombre, List<CustomActividad> actividades) {
		super();
		this.nombre = nombre;
		this.actividades = actividades;
	}

	public CustomPropiedad(int idPropiedad, String nombre, List<CustomActividad> actividades) {
		super();
		this.idPropiedad = idPropiedad;
		this.nombre = nombre;
		this.actividades = actividades;
	}

	public int getIdPropiedad() {
		return idPropiedad;
	}

	public void setIdPropiedad(int idPropiedad) {
		this.idPropiedad = idPropiedad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CustomActividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<CustomActividad> actividades) {
		this.actividades = actividades;
	}
}
