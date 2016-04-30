package resources;

import java.net.URI;
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

import dao.MyBatisManager;
import vo.CustomAlquiler;
import vo.CustomCliente;

@Path("/alquileres")
public class AlquileresResource {

	@Context
	UriInfo	uriInfo;

	@Context
	Request	request;

	@GET
	@Path("/cliente/{dniCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomAlquiler> getAlquileresCliente(@PathParam("dniCliente") String dniCliente) {
		return MyBatisManager.getInstance().getClienteByDNI(Integer.parseInt(dniCliente), true).getAlquileres();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newAlquiler(CustomAlquiler alquiler) {
		Response res;
		CustomCliente cliente = MyBatisManager.getInstance().getClienteByDNI(alquiler.getCliente().getDni(), true);
		if (cliente == null) {
			res = Response.status(409).entity("Post: The client doesn't exist").build();
		} else {
			URI uri = uriInfo.getAbsolutePathBuilder().path("alquiler").path(Integer.toString(alquiler.getIdAlquiler())).build();
			res = Response.created(uri).entity(alquiler).build();
			MyBatisManager.getInstance().addAlquiler(alquiler);
		}
		return res;
	}

	@Path("/alquiler/{id}")
	public AlquilerResource getAlquiler(@PathParam("id") String id) {
		return new AlquilerResource(id);
	}
}
