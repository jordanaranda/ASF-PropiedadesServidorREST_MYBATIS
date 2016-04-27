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

import dao.Cliente;
import utilities.HibernateManager;

@Path("/clientes")
public class ClientesResource {

	@Context
	UriInfo	uriInfo;

	@Context
	Request	request;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> getClients() {
		return HibernateManager.getInstance().getAllClientes();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newClient(Cliente client) {
		Response res = null;

		Cliente cliente = HibernateManager.getInstance().getClienteByDNI(client.getDni());
		if (cliente != null) {
			res = Response.status(409).entity("Post: Client with " + client.getDni() + " already exists").build();
		} else {
			URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(client.getDni())).build();
			res = Response.created(uri).entity(client).build();
			HibernateManager.getInstance().addCliente(client);
		}
		return res;
	}

	@Path("{dni}")
	public ClienteResource getCliente(@PathParam("dni") String dni) {
		return new ClienteResource(dni);
	}
}