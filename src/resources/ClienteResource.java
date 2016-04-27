package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.NotFoundException;

import dao.Cliente;
import utilities.HibernateManager;

public class ClienteResource {

	private int dni;

	public ClienteResource(String dni) {
		this.dni = Integer.parseInt(dni);
		Cliente cliente = HibernateManager.getInstance().getClienteByDNI(this.dni);
		if (cliente == null) {
			throw new NotFoundException("Get: Client with " + dni + " not found");
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente getClient() {
		return HibernateManager.getInstance().getClienteByDNI(dni);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putClient(@Context UriInfo uriInfo, Cliente client) {
		Response res;
		if (dni != client.getDni()) {
			res = Response.status(409).entity("Put: Client with " + client.getDni() + " does not match with current client").build();
		} else {
			res = Response.noContent().build();
			HibernateManager.getInstance().editCliente(client);
		}
		return res;
	}

	@DELETE
	public void deleteClient() {
		Cliente cliente = HibernateManager.getInstance().getClienteByDNI(dni);
		if (cliente != null) {
			HibernateManager.getInstance().deleteCliente(cliente);
		}
	}
}