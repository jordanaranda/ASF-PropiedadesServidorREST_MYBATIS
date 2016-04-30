package vo;

import java.util.Date;

public class Alquiler {

	private int		idAlquiler;
	private int		idPropiedad;
	private int		dniCliente;
	private int		idActividad;
	private Date	fecha_inicio;
	private Date	fecha_fin;
	private double	precio;

	public Alquiler() {

	}

	public Alquiler(int idPropiedad, int dniCliente, int idActividad, Date fechaInicio, Date fechaFin, double precio) {
		this.idPropiedad = idPropiedad;
		this.dniCliente = dniCliente;
		this.idActividad = idActividad;
		this.fecha_inicio = fechaInicio;
		this.fecha_fin = fechaFin;
		this.precio = precio;
	}

	public Alquiler(int idAlquiler, int idPropiedad, int dniCliente, int idActividad, Date fechaInicio, Date fechaFin, double precio) {
		this.idAlquiler = idAlquiler;
		this.idPropiedad = idPropiedad;
		this.dniCliente = dniCliente;
		this.idActividad = idActividad;
		this.fecha_inicio = fechaInicio;
		this.fecha_fin = fechaFin;
		this.precio = precio;
	}

	public int getIdAlquiler() {
		return idAlquiler;
	}

	public void setIdAlquiler(int idAlquiler) {
		this.idAlquiler = idAlquiler;
	}

	public int getIdPropiedad() {
		return idPropiedad;
	}

	public void setIdPropiedad(int idPropiedad) {
		this.idPropiedad = idPropiedad;
	}

	public int getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(int dniCliente) {
		this.dniCliente = dniCliente;
	}

	public int getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public CustomAlquiler toCustomAlquiler() {
		CustomAlquiler ca = new CustomAlquiler();
		ca.setIdAlquiler(idAlquiler);
		ca.setFecha_inicio(fecha_inicio);
		ca.setFecha_fin(fecha_fin);
		ca.setPrecio(precio);
		return ca;
	}
}