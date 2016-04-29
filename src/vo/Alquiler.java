package vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Alquiler {

	private int idAlquiler;
	private int idPropiedad;
	private int dniCliente;
	private int idActividad;
	private Date fechaInicio;
	private Date fechaFin;
	private double precio;

	public Alquiler() {

	}

	public Alquiler(int idPropiedad, int dniCliente, int idActividad, Date fechaInicio, Date fechaFin, double precio) {
		super();
		this.idPropiedad = idPropiedad;
		this.dniCliente = dniCliente;
		this.idActividad = idActividad;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
	}



	public Alquiler(int idAlquiler, int idPropiedad, int dniCliente, int idActividad, Date fechaInicio, Date fechaFin,
			double precio) {
		super();
		this.idAlquiler = idAlquiler;
		this.idPropiedad = idPropiedad;
		this.dniCliente = dniCliente;
		this.idActividad = idActividad;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}