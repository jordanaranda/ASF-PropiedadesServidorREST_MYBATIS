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

import utilities.HibernateManager;
import vo.Alquiler;
import vo.Cliente;

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
		return HibernateManager.getInstance().getClienteByDNI(Integer.parseInt(dniCliente)).getAlquileres();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newAlquiler(Alquiler alquiler) {
		Response res;
		Cliente cliente = HibernateManager.getInstance().getClienteByDNI(alquiler.getCliente().getDni());
		if (cliente == null) {
			res = Response.status(409).entity("Post: The client doesn't exist").build();
		} else {
			URI uri = uriInfo.getAbsolutePathBuilder().path("alquiler").path(Integer.toString(alquiler.getIdAlquiler())).build();
			res = Response.created(uri).entity(alquiler).build();
			cliente.getAlquileres().add(alquiler);
			HibernateManager.getInstance().editCliente(cliente);
		}
		return res;
	}

	@Path("/alquiler/{id}")
	public AlquilerResource getAlquiler(@PathParam("id") String id) {
		return new AlquilerResource(id);
	}
}
