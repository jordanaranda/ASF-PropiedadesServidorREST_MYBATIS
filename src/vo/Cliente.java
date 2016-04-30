package vo;

public class Cliente {

	private int		dni;
	private String	nombre;
	private String	apellido;
	private String	email;
	private String	direccion;
	private int		cp;
	private int		telefono;

	public Cliente() {

	}

	public Cliente(String nombre, String apellido, String email, String direccion, int cp, int telefono) {

	}

	public Cliente(int dni, String nombre, String apellido, String email, String direccion, int cp, int telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
		this.cp = cp;
		this.telefono = telefono;
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

	public int getcp() {
		return cp;
	}

	public void setcp(int cp) {
		this.cp = cp;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public CustomCliente toCustomCliente() {
		CustomCliente cc = new CustomCliente();
		cc.setDni(dni);
		cc.setNombre(nombre);
		cc.setApellido(apellido);
		cc.setEmail(email);
		cc.setDireccion(direccion);
		cc.setCodigoPostal(cp);
		cc.setTelefono(telefono);
		return cc;
	}
}