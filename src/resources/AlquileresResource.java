package resources;

import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import dao.Actividad;
import dao.Alquiler;
import dao.Cliente;
import dao.Propiedad;
import utilities.Database;

@Path("/alquileres")
public class AlquileresResource {

	@Context
	UriInfo	uriInfo;

	@Context
	Request	request;

	@GET
	@Path("/cliente/{dniCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alquiler> getAlquileresCliente(@PathParam("dniCliente") String dniCliente) {
		List<Alquiler> alquileres = new ArrayList<Alquiler>();
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult("select * from alquiler where dniCliente = " + Integer.parseInt(dniCliente));
			while (rs.next()) {
				alquileres.add(new Alquiler(rs.getInt("idAlquiler"), getClientePorDni(rs.getInt("dniCliente")),
						getActividadPorId(rs.getInt("idActividad")), getPropiedadPorId(rs.getInt("idPropiedad")),
						new Date(rs.getLong("fecha_inicio")), new Date(rs.getLong("fecha_fin")), rs.getDouble("precio")));
			}
			Database.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alquileres;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newAlquiler(Alquiler alquiler) {
		Response res = null;
		Database.getInstance().createConnection();
		if (Database.getInstance().count("alquiler", "idAlquiler = " + alquiler.getIdAlquiler()) > 0) {
			res = Response.status(409).entity("Post: Alquiler with " + alquiler.getIdAlquiler() + " already exists").build();
		} else {
			URI uri = uriInfo.getAbsolutePathBuilder().path("alquiler").path(Integer.toString(alquiler.getIdAlquiler())).build();
			res = Response.created(uri).entity(alquiler).build();
			Database.getInstance()
					.update("insert into alquiler (idPropiedad, dniCliente, idActividad, fecha_inicio, fecha_fin, precio) values ("
							+ alquiler.getPropiedad().getId() + ", " + alquiler.getCliente().getDni() + ", " + alquiler.getActividad().getId() + ", "
							+ alquiler.getFechaInicio().getTime() + ", " + alquiler.getFechaFin().getTime() + ", " + alquiler.getPrecio() + ")");
		}
		Database.getInstance().disconnect();
		return res;
	}

	@Path("/alquiler/{id}")
	public AlquilerResource getAlquiler(@PathParam("id") String id) {
		return new AlquilerResource(id);
	}

	private Cliente getClientePorDni(int dni) {
		Cliente cliente = null;
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult("select * from cliente where dni = " + dni);
			if (rs.next()) {
				cliente = new Cliente(rs.getInt("dni"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"),
						rs.getString("direccion"), rs.getInt("cp"), rs.getInt("telefono"));
			}
			Database.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

	private Actividad getActividadPorId(int idActividad) {
		Actividad actividad = null;
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult("select * from actividad where idActividad = " + idActividad);
			if (rs.next()) {
				actividad = new Actividad(rs.getInt("idActividad"), rs.getString("nombre"));
			}
			Database.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actividad;
	}

	private Propiedad getPropiedadPorId(int idPropiedad) {
		Propiedad propiedad = null;
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult("select * from propiedad where idPropiedad = " + idPropiedad);
			if (rs.next()) {
				propiedad = new Propiedad(rs.getInt("idPropiedad"), rs.getString("nombre"));
			}
			Database.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return propiedad;
	}
}
