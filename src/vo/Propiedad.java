package vo;

public class Propiedad {

	private int		idPropiedad;
	private String	nombre;
	private String	descripcion;
	private String	direccion;
	private float	latitud;
	private float	longitude;
	private double	area;
	private double	precio;
	private int		idProvincia;

	public Propiedad() {

	}

	public Propiedad(int idPropiedad, String nombre, String descripcion, String direccion, float latitud, float longitude, double area, double precio,
			int idProvincia) {
		this.idPropiedad = idPropiedad;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitude = longitude;
		this.area = area;
		this.precio = precio;
		this.idProvincia = idProvincia;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public CustomPropiedad toCustomPropiedad() {
		CustomPropiedad cp = new CustomPropiedad();
		cp.setNombre(nombre);
		cp.setIdPropiedad(idPropiedad);
		return cp;
	}
}