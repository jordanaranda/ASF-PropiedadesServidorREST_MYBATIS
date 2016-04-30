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

import dao.MyBatisManager;
import vo.CustomCliente;

public class ClienteResource {

	private int dni;

	public ClienteResource(String dni) {
		this.dni = Integer.parseInt(dni);
		CustomCliente cliente = MyBatisManager.getInstance().getClienteByDNI(this.dni, false);
		if (cliente == null) {
			throw new NotFoundException("Get: Client with " + dni + " not found");
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CustomCliente getClient() {
		return MyBatisManager.getInstance().getClienteByDNI(dni, true);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putClient(@Context UriInfo uriInfo, CustomCliente client) {
		Response res;
		if (dni != client.getDni()) {
			res = Response.status(409).entity("Put: Client with " + client.getDni() + " does not match with current client").build();
		} else {
			res = Response.noContent().build();
			MyBatisManager.getInstance().editCliente(client);
		}
		return res;
	}

	@DELETE
	public void deleteClient() {
		CustomCliente cliente = MyBatisManager.getInstance().getClienteByDNI(dni, false);
		if (cliente != null) {
			MyBatisManager.getInstance().deleteCliente(cliente);
		}
	}
}