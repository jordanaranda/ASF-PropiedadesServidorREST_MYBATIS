package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.NotFoundException;

import dao.Alquiler;
import utilities.Database;

public class AlquilerResource {

	private int id;

	public AlquilerResource(String id) {
		this.id = Integer.parseInt(id);
		Database.getInstance().createConnection();
		if (Database.getInstance().count("alquiler", "idAlquiler = " + id) == 0)
			throw new NotFoundException("Get: Alquiler with " + id + " not found");
		Database.getInstance().disconnect();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putAlquiler(@Context UriInfo uriInfo, Alquiler alquiler) {
		Response res;
		Database.getInstance().createConnection();
		if (id != alquiler.getIdAlquiler()) {
			res = Response.status(409).entity("Put: Alquiler with " + alquiler.getIdAlquiler() + " does not match with current client").build();
		} else {
			res = Response.noContent().build();
			Database.getInstance()
					.update("update alquiler set idPropiedad = " + alquiler.getPropiedad().getId() + ", dniCliente = "
							+ alquiler.getCliente().getDni() + ", idActividad = " + alquiler.getActividad().getId() + ", fecha_inicio = "
							+ alquiler.getFechaInicio().getTime() + ", fecha_fin = " + alquiler.getFechaFin().getTime() + ", precio = "
							+ alquiler.getPrecio() + " where idAlquiler = " + id);
		}
		Database.getInstance().disconnect();
		return res;
	}

	@DELETE
	public void deleteAlquiler() {
		Database.getInstance().createConnection();
		Database.getInstance().update("delete from alquiler where idAlquiler = " + id);
		Database.getInstance().disconnect();
	}

}
